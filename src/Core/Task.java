package Core;
import java.util.List;

@SuppressWarnings("serial")
public class Task extends Activity {

	/**
	 * isStartable: Used to control when a task can be turned on
	 * @uml.property name="isStartable"
	 */
	private Boolean isStartable;

	/**
	 * intervals: List of intervals 
	 * @uml.property name="intervals"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 * aggregation="composite" inverse="task:Core.Interval"
	 */
	private List<Interval> intervals;

	/**
	 * Constructor task: It complains the leaf element of the composite design pattern
	 * We call the constructor of the superclass and initialize the list of intervals 
	 * and the parameter isStartable, that we use to control if a task is not turned 
	 * on yet
	 * @param name: Name of the task
	 * @param description: Description of the task
	 * @param project: Father project of the task
	 */
	public Task(String name, String description, Project project) {

		super(name, description, project);
		this.intervals = new java.util.ArrayList<Interval>();
		this.isStartable = true;
	}

	/**
	 * start: If the task is able to start we create an interval and add it to the list 
	 * intervals of the task and clock observers and change the state of the 
	 * startable to false. If isn't able to start it throws a warning
	 * @param clock: Needed to create the observer
	 */
	public void start(Clock clock) {

		if (this.isStartable == true) {

			Interval interval = new Interval(this);
			this.intervals.add(interval);
			clock.addObserver(interval);
			this.isStartable = false;

		} else {

			logger.warn("The task is already running");
		}
		logger.info("New interval added to: "+this.name);
	}

	/**
	 * stop: To stop a task, it delete the observer of the clock and change
	 * the state
	 * @param clock: Needed to delete the observer
	 */
	public void stop(Clock clock) {
		// Delete the observer of the last interval
		clock.deleteObserver(this.intervals.get(this.intervals.size()-1));
		this.isStartable = true;
	}

	/**
	 * @Override toString: Used to print a task
	 * Controls the format of the time
	 */
	@Override
	public String toString() {

		int seconds = (int) (this.duration / 1000) % 60;
		int minutes = (int) ((this.duration / (1000 * 60)) % 60);
		int hours = (int) ((this.duration / (1000 * 60 * 60)) % 24);

		if( this.startDate == null ){

			return this.name + "   " + "                             " +
							   "   " + "                             " +
					"   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
		}

		return this.name + "   " + this.startDate +
				"   " + this.finishDate +
				"   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	/**
	 * accept: Function part of the visitor design pattern that we use to print 
	 * @param printer: Object of the class printer used to visit the activity
	 */
	@Override
	public void accept(Printer printer) {

		printer.print(this);
	}

}
