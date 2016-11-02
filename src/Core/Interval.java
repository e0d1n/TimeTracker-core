package core;
import java.util.Date;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class Interval implements Observer, Serializable{

	/** Declaration of the logger
	 *  used for debugging purposes
	 */
	static Logger logger = LoggerFactory.getLogger("Interval");
	
	/**
     * startDate: Start date of a interval
     * @uml.property   name="startDate"
     */
	private Date startDate;

	/**
     * finishDate: Finish date of a interval
     * @uml.property   name="finishDate"
     */
	private Date finishDate;

	/**
     * task: What task has created this interval
     * @uml.property   name="task"
     * @uml.associationEnd   multiplicity="(1 1)" inverse="intervals:core.Task"
     */
	private Task task;

	/**
     * duration: Duration of a interval
     * @uml.property   name="duration"
     */
	private Long duration;

	/**
	 * Interval constructor: It's used to create an interval. At first it defines 
	 * the father task and put the initial values of the other attributes, 
	 * at the case of the dates we use null to control the first iteration
	 * @param task: Father task of interval
	 */
	public Interval(Task task) {
		
		this.task = task;
		this.startDate = null;
		this.finishDate = null;
		this.duration = (long) 0;
	}

	/**
	 * update: Implements the update method of observer
	 * At first we update the finish date. 
	 * If the start date is null it means we are at the first call of the update 
	 * and we initialize the start date and call the method updateActivity with 
	 * the parameter true to indicate the first iteration
	 * Else we update the duration and call updateActivity with false
	 * clock: Clock to update dates and duration
	 */
	@Override
	public void update(Observable arg0, Object clock) {

		this.finishDate = ((Clock) clock).getDate();
		if (this.startDate == null) {
			this.startDate = ((Clock) clock).getDate();
			this.task.updateActivity(clock,true);
		}else{
			this.duration = this.duration + ((Clock) clock).getUpdatePeriod();
			this.task.updateActivity(clock,false);
		}

		logger.debug("Interval updated from task " + this.task.name);
	}

	/**
	 * @Override toString: Used to print a interval
	 * Controls the format of the time
	 */
	@Override
	public String toString() {
		
		int seconds = (int) (this.duration / 1000) % 60;
		int minutes = (int) ((this.duration / (1000 * 60)) % 60);
		int hours = (int) ((this.duration / (1000 * 60 * 60)) % 24);
		return "Interval " + this.task.name + ", Duracion:" + String.format(("%02d:%02d:%02d"), hours, minutes, seconds);
	}

}
