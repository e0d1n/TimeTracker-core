package core;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.TableVisitor;
import reports.Taula;

@SuppressWarnings("serial")
public class Project extends Activity {

    static Logger logger = LoggerFactory.getLogger("Project");

    /**
     * activities: List with activities
     * @uml.property   name="activities"
     * @uml.associationEnd   readOnly="true" ordering="true" aggregation="composite" inverse="project:core.Activity"
     */
    private List<Activity> activities;
    
    public List<Activity> getActivities(){
    	return this.activities;
    }

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

        int seconds = (int) (this.periode.getDuration() % 60);
        int minutes = (int) ((this.periode.getDuration() / (60)) % 60);
        int hours = (int) ((this.periode.getDuration() / (60 * 60)) % 24);

        if( this.periode.getDataInici() == null ){

            return this.name + "   " + "                             " +
                    		"   " + "                             " +
                    "   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

        return this.name + "   " + this.periode.getDataInici() +
                "   " + this.periode.getDataFi() +
                "   " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * accept: Function part of the visitor design pattern that we use to print
     * Call all the accept of all the elements at the activities list 
     * @param printer: Object of the class printer used to visit the activity
     */
    @Override
    public void acceptPrinter(Printer printer) {

        printer.print(this);
        // Call each of it sub activities
        for (Activity act:this.activities){
            act.acceptPrinter(printer);
        }
    }
    
    public void acceptTableVisitor(TableVisitor tableVisitor, Taula table, Periode periode){
    	
    	tableVisitor.visitProject(this, table, periode);
    	
    }

}
