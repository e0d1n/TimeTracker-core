package Core;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Date;

@SuppressWarnings("serial")
public class Interval implements Observer, Serializable,Printable {

	/**
	 * startDate: Start date of a interval
	 * @uml.property name="startDate"
	 */
	private Date startDate;

	/**
	 * finishDate: Finish date of a interval
	 * @uml.property name="finishDate"
	 */
	private Date finishDate;

	/**
	 * task: Who has created this interval
	 * @uml.property name="task"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="intervals:Core.Task"
	 */
	private Task task;

	/**
	 * duration: Duration of a interval
	 * @uml.property name="duration"
	 */
	private Long duration;

	/**
	 * Getter of the property <tt>duration</tt>
	 * @return  Returns the duration.
	 * @uml.property  name="duration"
	 */
	public long getDuration() {
		return this.duration;
	}
	
	/**
	 * Interval constructor: It's used to create an interval of a Task
	 * @param task: Father task of interval
	 */
	public Interval(Task task) {
		this.task = task;
		this.startDate = null;
		this.finishDate = null;
		this.duration = (long) 0;
	}
	
	/**
	 * update: Implements the update method observer
	 * Update a interval and calls the method to update 
	 * the activity of this interval (this updates upwards)
	 * clock: Clock to update dates and duration
	 */
	@Override
	public void update(Observable arg0, Object clock) {
		// TODO Auto-generated method stub
		if (this.startDate == null) {
			this.startDate = ((Clock) clock).getDate();
			this.duration = (long) 0;
		}else{
			this.duration = this.duration + ((Clock) clock).getUpdatePeriode();
		}
		this.finishDate = ((Clock) clock).getDate();	
		this.task.updateActivity(clock);
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
		return "Soy interval, Duracion:"+ String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	@Override
	public void accept(Printer printer) {
		// TODO Auto-generated method stub
		printer.print(this);
		
	}
}
