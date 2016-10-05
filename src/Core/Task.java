package Core;

import java.util.List;

public class Task extends Activity {

	/**
	 * @uml.property name="isStartable"
	 */
	private Boolean isStartable;

	/**
	 * @uml.property name="intervals"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     aggregation="composite" inverse="task:Core.Interval"
	 */
	private List<Interval> intervals;

	public Task(String name, String description, Project project) {
		// TODO Auto-generated constructor stub
		super(name, description, project);
		this.intervals = new java.util.ArrayList<Interval>();
		this.isStartable = true;

	}

	public void addInterval(Task task) {
		Interval newInterval = new Interval(task);
		this.intervals.add(newInterval);
	}
	

	/**
	 */
	public void start(Clock clock) {
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
	 */
	public void stop(Clock clock) {
		clock.deleteObserver(this.intervals.get(this.intervals.size()-1));
		this.isStartable = true;
	}

	@Override
	public String toString() {
		int seconds = (int) (this.duration / 1000) % 60;
		int minutes = (int) ((this.duration / (1000 * 60)) % 60);
		int hours = (int) ((this.duration / (1000 * 60 * 60)) % 24);
		return "Soy Task " + this.name + " Start:" + startDate + " Finish:" + finishDate +" Duration:"+String.format("%02d:%02d:%02d", hours, minutes, seconds);	
	}

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

		

}
