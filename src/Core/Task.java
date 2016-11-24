package core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.TableVisitor;
import reports.Taula;

@SuppressWarnings("serial")
public class Task extends Activity {
	
	/**
	 * Declaration of the logger used for debugging purposes
	 */
	private static Logger logger = LoggerFactory.getLogger("Task");
	
	/**
	 * isStartable: Used to control when a task can be turned on
	 * 
	 * @uml.property name="isStartable"
	 */
	private Boolean isStartable;
	
	/**
	 * intervals: List of intervals
	 * 
	 * @uml.property name="intervals"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     aggregation="composite" inverse="task:core.Interval"
	 */
	private List<Interval> intervals;
	
	public final List<Interval> getIntervals() {
		return this.intervals;
	}
	
	private boolean invariant(){
		
		if (this.getProject() == null){
			return false;
		}
		
		if (this.getIntervals() == null){
			return false;
		}
		
		if (this.getName() == null){
			return false;
		}
		
		if (this.getPeriode() == null){
			return false;
		}
		
		if (this.isStartable == null){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Constructor task: It complains the leaf element of the composite design
	 * pattern We call the constructor of the superclass and initialize the list
	 * of intervals and the parameter isStartable, that we use to control if a
	 * task is not turned on yet
	 * 
	 * @param name
	 *            : Name of the task
	 * @param description
	 *            : Description of the task
	 * @param project
	 *            : Father project of the task
	 */
	public Task(final String name, final String description,
	        final Project project) {
		
		super(name, description, project);
		this.intervals = new java.util.ArrayList<Interval>();
		this.isStartable = true;
		assert invariant();
	}
	
	/**
	 * start: If the task is able to start we create an interval and add it to
	 * the list intervals of the task and clock observers and change the state
	 * of the startable to false. If isn't able to start it throws a warning
	 * 
	 * @param clock
	 *            : Needed to create the observer
	 */
	public final void start(final Clock clock) {
		assert clock != null;
		if (this.isStartable) {
			
			Interval interval = new Interval(this);
			this.intervals.add(interval);
			clock.addObserver(interval);
			this.isStartable = false;
			
			logger.debug("New interval added to: " + this.name);
			
		} else {
			
			logger.warn("The task is already running");
		}
		assert invariant();
	}
	
	/**
	 * stop: To stop a task, it delete the observer of the clock and change the
	 * state
	 * 
	 * @param clock
	 *            : Needed to delete the observer
	 */
	public final void stop(final Clock clock) {
		assert clock != null;
		// Delete the observer of the last interval
		if (this.intervals.size() == 0){
			throw new RuntimeException("You can't stop a task that has not been started");
		}
		clock.deleteObserver(this.intervals.get(this.intervals.size() - 1));
		this.isStartable = true;
		logger.debug("Task stopped " + this.name);
		assert invariant();
	}
	
	/**
	 * @Override toString: Used to print a task Controls the format of the time
	 */
	@Override
	public final String toString() {
		
		if (this.periode.getDataInici() == null) {
			
			return this.name + "   " + "                     " + "   "
			        + "                     " + "   "
			        + this.periode.getDurationAsStringFormated();
		}
		
		return this.name + "   " + this.periode.getDataIniciAsStringFormated()
		        + "   " + this.periode.getDataFiAsStringFormated() + "   "
		        + this.periode.getDurationAsStringFormated();
	}
	
	/**
	 * accept: Function part of the visitor design pattern that we use to print
	 * 
	 * @param printer
	 *            : Object of the class printer used to visit the activity
	 */
	@Override
	public final void acceptPrinter(final Printer printer) {
		assert printer != null;
		printer.print(this);
		assert invariant();
	}
	
	public final void acceptTableVisitor(final TableVisitor tableVisitor,
	        final Taula table, final Periode periode) {
		assert tableVisitor != null;
		assert table != null;
		assert periode != null;
		tableVisitor.visitTask(this, table, periode);
		assert invariant();
	}
	
}
