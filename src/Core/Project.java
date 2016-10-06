package Core;

import java.util.List;

public class Project extends Activity {

	/** 
	 * @uml.property name="activities" readOnly="true" ordering="true"
	 * @uml.property name="activities"
	 * @uml.associationEnd readOnly="true" ordering="true" aggregation="composite" inverse="project:Core.Activity"
	 */
	private List activities;

	public Project(String name, String description, Project project) {
		super(name, description, project);
		this.activities = new java.util.ArrayList<Activity>();
		// TODO Auto-generated constructor stub
	}

	/**
	 */
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}

	@Override
	public String toString() {
		int seconds = (int) (this.duration / 1000) % 60;
		int minutes = (int) ((this.duration / (1000 * 60)) % 60);
		int hours = (int) ((this.duration / (1000 * 60 * 60)) % 24);

		return "Soy project " + this.name + " Start:" + startDate + " Finish:" + finishDate +" Duration:" +String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	/**
	 * Getter of the property <tt>activities</tt>
	 * @return  Returns the activities.
	 * @uml.property  name="activities"
	 */
	public List getActivities(String name) {////ACABAR
		return activities;
	}
}
