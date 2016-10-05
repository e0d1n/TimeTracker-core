package Core;

import java.util.Date;

public abstract class Activity {

	/**
	 * @uml.property name="name"
	 */
	protected String name;

	/**
	 * @uml.property name="description"
	 */
	protected String description;

	/**
	 * @uml.property name="finishDate"
	 */
	protected Date finishDate;

	/**
	 * @uml.property name="startDate"
	 */
	protected Date startDate;
	
	/**
	 * @uml.property name="project"
	 * @uml.associationEnd multiplicity="(1 1)"
	 *                     inverse="activities:Core.Project"
	 */
	protected Project project;

	/**
	 * @uml.property name="duration"
	 */
	protected Long duration;

	/**
	 * Activity constructor It's used to create heritage objects
	 * 
	 * @param name
	 *            : Name of the activity
	 * @param description
	 *            : Description of the activity
	 * @param project
	 *            : Father project of the activity
	 */
	public Activity(String name, String description, Project project) {
		this.name = name;
		this.description = description;
		this.project = project;
		this.startDate = null;
		this.finishDate = null;
	}

	/**
	 * Activity Recursive Update It's used to update the activity time.
	 * 
	 * @param date
	 *            : Last update time
	 */
	protected void updateActivity(Date date) {
		// No start date defined
		if (this.startDate == null) {
			this.startDate = date;
		}
		this.finishDate = date;
		System.out.println(this);
		if (this.project != null) {
			this.project.updateActivity(date);
		}

	}

	

}
