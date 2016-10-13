package Core;
import java.util.List;

@SuppressWarnings("serial")
public class Project extends Activity {

	/** 
	 * activities: List with activities
	 * @uml.property name="activities" readOnly="true" ordering="true"
	 * @uml.property name="activities"
	 * @uml.associationEnd readOnly="true" ordering="true" aggregation="composite" inverse="project:Core.Activity"
	 */
	private List<Activity> activities;

	/**
	 * Constructor project: Used to create a project
	 * @param name: Name of the project
	 * @param description: Description of the project
	 * @param project: Father project of the project
	 */
	public Project(String name, String description, Project project) {
		// TODO Auto-generated constructor stub
		super(name, description, project);
		this.activities = new java.util.ArrayList<Activity>();
	}
	

	/**
	 * addActivity: adds a activity to a project
	 * @param activity: the activity to add
	 */
	public void addActivity(Activity activity) {
		this.activities.add(activity);
		logger.info("New activity added: "+activity.name);
	}

	/**
	 * @Override toString: Used to print a project
	 * Controls the format of the time
	 */
	@Override
	public String toString() {
		int seconds = (int) (this.duration / 1000) % 60;
		int minutes = (int) ((this.duration / (1000 * 60)) % 60);
		int hours = (int) ((this.duration / (1000 * 60 * 60)) % 24);
		
		// 5-Set-2016 19:00:00
		if( this.startDate == null ){
			return this.name + "   " + "                             "+"   "+ "                             " +"   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
		}
		
		return this.name + "   " +this.startDate +"   " + this.finishDate +"   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
		
		//return "Soy project " + this.name + " Start:" + startDate + " Finish:" + finishDate +" Duration:" +String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	@Override
	public void accept(Printer printer) {
		// TODO Auto-generated method stub
		printer.print(this);
		for (Activity act:this.activities){
			act.accept(printer);
		}
		
	}

}
