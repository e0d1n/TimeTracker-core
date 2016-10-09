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
	 * Getter of the property <tt>duration</tt>
	 * @return  Returns the duration.
	 * @uml.property  name="duration"
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Setter of the property <tt>duration</tt>
	 * @param duration  The duration to set.
	 * @uml.property  name="duration"
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	/**
	 * Constructor task: Used to create a task
	 * @param name: Name of the task
	 * @param description: Description of the task
	 * @param project: Father project of the task
	 */
	public Task(String name, String description, Project project) {
		// TODO Auto-generated constructor stub
		// Use super type activity constructor
		super(name, description, project);
		this.intervals = new java.util.ArrayList<Interval>();
		this.isStartable = true;
	}

	/**
	 * addInterval: Used to add interval to a task and
	 * adds the interval to the interval list task
	 * @param task: Necessary to specify that task creates the interval
	 */
	public void addInterval(Task task) {
		Interval newInterval = new Interval(task);
		this.intervals.add(newInterval);
	}
	
	/**
	 * start: Start a task creating an interval
	 * It has an observer to be notified by the clock
	 * @param clock: Needed to create the observer
	 */
	public void start(Clock clock) {
		// Check if the task can be started
		if (this.isStartable == true) {
			Interval interval = new Interval(this);
			this.intervals.add(interval);
			clock.addObserver(interval);
			this.isStartable = false;
		} else {
			System.out.println("This task is already running");
		}
	}

	/**
	 * stop: Stop a task
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
		return "Soy Task " + this.name + " Start:" + startDate + " Finish:" + finishDate +" Duration:"+String.format("%02d:%02d:%02d", hours, minutes, seconds);	
	}
	
	@Override
	public void accept(Printer printer) {
		// TODO Auto-generated method stub
		printer.print(this);
		
	}

		

}
