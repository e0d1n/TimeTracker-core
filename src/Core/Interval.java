package Core;

import java.util.Observable;
import java.util.Observer;
import java.util.Date;

public class Interval implements Observer {

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
	}

	/**
	 * 
	 */
	@Override
	public String toString() {

		int seconds = (int) (this.duration / 1000) % 60;
		int minutes = (int) ((this.duration / (1000 * 60)) % 60);
		int hours = (int) ((this.duration / (1000 * 60 * 60)) % 24);

		return "	Soy interval "
				+ String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	/**
	 * Implements the update method of the observer Update the father task of a
	 * interval with the new dates
	 */
	@Override
	public void update(Observable arg0, Object date) {
		// TODO Auto-generated method stub
		if (this.startDate == null) {
			this.startDate = (Date) date;
		}
		this.finishDate = (Date) date;
		this.duration = getDateDiff(this.startDate, this.finishDate);
		System.out.println(this);
		this.task.updateActivity((Date) date);
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
