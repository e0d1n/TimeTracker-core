package Core;
import java.util.Date;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public abstract class Activity implements Serializable, Printable {
	
	/** Declaration of the logger
	 *  used for debugging purposes
	 */
	static Logger logger = LoggerFactory.getLogger("Activity");
	
	/**
	 * name: Name of the Activity
	 * @uml.property name="name"
	 */
	protected String name;

	/**
	 * description: Description of the activity
	 * @uml.property name="description"
	 */
	protected String description;

	/**
	 * finishDate: Finish date of the activity
	 * @uml.property name="finishDate" 
	 */
	protected Date finishDate;

	/**
	 * startDate: Start date of the activity
	 * @uml.property name="startDate" 
	 */
	protected Date startDate;

	/**
	 * project: An activity has a project (father project)
	 * @uml.property name="project"
	 * @uml.associationEnd multiplicity="(1 1)"
	 * inverse="activities:Core.Project" Activity
	 */
	protected Project project;

	/**
	 * duration: Duration of the activity
	 * @uml.property name="duration" 
	 */
	protected Long duration;

	/**
	 * Constructor Activity: Activity is a abstract class for project and task 
	 * This class is the component of the composite design
	 * We needed this pattern because we have to contain in a project, tasks and other projects
	 * This function starts the dates to null because we need it to control when a activity
	 * is not started, to assign both
	 * Finally it assign the level of the logger
	 * @param name: Name of the activity
	 * @param description: Description of the activity
	 * @param project: Father project of the activity, can be null for the root project
	 */
	public Activity(String name, String description, Project project) {
		this.name = name;
		this.description = description;
		this.project = project;
		this.startDate = null;
		this.finishDate = null;
		this.duration = (long) 0;
	}

	/**
	 * updateActivity: It's used to update the dates and duration of the activity
	 * At first we watch the dates state and we use the null state for determinate
	 * if is the first iteration in the activity, it is required to control the
	 * assignment of the start date. This along with the parameter "first" we control
	 * the initialization and update of the duration.
	 * Update the finish date with clock
	 * At last we call this function at the parent, if the activity have it 	 
	 * @param first: Control if the interval at the tree leaf is not started before
	 * @param clock: Used watch for updates
	 */
	protected void updateActivity(Object clock, boolean first) {
		
		// If no start date defined
		if (this.startDate == null) {
			this.startDate = ((Clock) clock).getDate();
			this.duration = (long) 0;
		} else if (!first){
			this.duration = this.duration + ((Clock) clock).getUpdatePeriod();
		}
		this.finishDate = ((Clock) clock).getDate();
		if (this.project != null) {
			this.project.updateActivity(clock,first);
		}
		
		logger.debug("Updated activity "+ this.name);
	}
}
