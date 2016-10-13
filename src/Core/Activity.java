package Core;
import java.util.Date;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("serial")
public abstract class Activity implements Serializable, Printable {
	
	Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	
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
	 * Constructor Activity: Is used to create tasks or projects
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
	 * updateActivity: It's used to update the dates 
	 * and duration of the activity and their parents dates
	 * @param first 
	 * @param clock: Used watch for updates
	 */
	protected void updateActivity(Object clock, boolean first) {
		// If no start date defined
		if (this.startDate == null) {
			this.startDate = ((Clock) clock).getDate();
			this.duration = (long) 0;
		} else if (!first){
			this.duration = this.duration + ((Clock) clock).getUpdatePeriode();
		}
		this.finishDate = ((Clock) clock).getDate();
		if (this.project != null) {
			this.project.updateActivity(clock,first);
		}
	}
}
