package core;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class Project extends Activity {

    static Logger logger = LoggerFactory.getLogger("Project");

    /**
     * activities: List with activities
     * @uml.property   name="activities"
     * @uml.associationEnd   readOnly="true" ordering="true" aggregation="composite" inverse="project:core.Activity"
     */
    private List<Activity> activities;

    /**
     * Constructor project: It complains the composite element of the composite design pattern
     * We call the constructor of the superclass and
     * initialize the activities list 
     * @param name: Name of the project
     * @param description: Description of the project
     * @param project: Father project of the project
     */
    public Project(String name, String description, Project project) {
        super(name, description, project);
        this.activities = new java.util.ArrayList<Activity>();
        logger.debug("Project created " + name);
    }

    /**
     * addActivity: It adds the activity that like a parameter 
     * to activities list
     * @param activity: the activity to add
     */
    public void addActivity(Activity activity) {

        this.activities.add(activity);
        logger.info("New activity added: " + activity.name);
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

        if( this.startDate == null ){

            return this.name + "   " + "                             " +
                    "   " + "                             " +
                    "   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

        return this.name + "   " + this.startDate +
                "   " + this.finishDate +
                "   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * accept: Function part of the visitor design pattern that we use to print
     * Call all the accept of all the elements at the activities list 
     * @param printer: Object of the class printer used to visit the activity
     */
    @Override
    public void accept(Printer printer) {

        printer.print(this);
        // Call each of it sub activities
        for (Activity act:this.activities){
            act.accept(printer);
        }
    }

}
