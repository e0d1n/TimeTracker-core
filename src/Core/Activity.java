package Core;
import java.io.Serializable;
import java.util.Date;

public abstract class Activity implements Serializable,Printable{

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
		this.duration = (long) 0;
	}

	/**
	 * Activity Recursive Update It's used to update the activity time.
	 * 
	 * @param Object clock
	 *            : Last update time
	 */
	protected void updateActivity(Object clock) {
		// No start date defined
		if (this.startDate == null) {
			this.startDate = ((Clock) clock).getDate();
			this.duration = (long) 0;
		}else{
		this.duration = this.duration + ((Clock) clock).getUpdatePeriode();			
		}
		this.finishDate = ((Clock) clock).getDate();
		
		if (this.project != null) {
			this.project.updateActivity(clock);
		}

	}

	

}
