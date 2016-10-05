package Core;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Date;

public class Interval implements Observer, Serializable {

	/**
	 * @uml.property name="startDate"
	 */
	private Date startDate;

	/**
	 * @uml.property name="finishDate"
	 */
	private Date finishDate;

	/**
	 * @uml.property name="task"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="intervals:Core.Task"
	 */
	private Task task;

	/**
	 * @uml.property name="duration"
	 */
	private Long duration;

	/**
	 * Interval constructor It's used to create an interval of a Task
	 * 
	 * @param task
	 *            : Father task of interval
	 * @param sstartDate
	 *            : Start date of interval
	 * @param finishDate
	 *            : Finish date of interval
	 */
	public Interval(Task task) {
		this.task = task;
		this.startDate = null;
		this.finishDate = null;
		this.duration = (long) 0;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {

		int seconds = (int) (this.duration / 1000) % 60;
		int minutes = (int) ((this.duration / (1000 * 60)) % 60);
		int hours = (int) ((this.duration / (1000 * 60 * 60)) % 24);

		return "Soy interval, Duracion:"+ String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	/**
	 * Implements the update method of the observer Update the father task of a
	 * interval with the new dates
	 */
	@Override
	public void update(Observable arg0, Object clock) {
		// TODO Auto-generated method stub
		if (this.startDate == null) {
			this.startDate = ((Clock) clock).getDate();
		}
		this.finishDate = ((Clock) clock).getDate();
		this.duration =this.duration + ((Clock) clock).getUpdatePeriode();
		
		System.out.println(this);
		this.task.updateActivity(clock);
	}

	/**
		 */
	public long getDateDiff(Date date1, Date date2) {

		return date2.getTime() - date1.getTime();

	}

	public long getDuration() {

		return this.duration;
	}
}
