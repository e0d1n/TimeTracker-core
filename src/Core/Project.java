package Core;

import java.util.List;

public class Project extends Activity {

	/**
	 * @uml.property name="activities"
	 * @uml.associationEnd readOnly="true" multiplicity="(0 -1)" ordering="true"
	 *                     aggregation="composite"
	 *                     inverse="project:Core.Activity"
	 */
	private List<Activity> activities;

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

		return "Soy project " + this.name + " " + startDate + " " + finishDate;
	}
}
