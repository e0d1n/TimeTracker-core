package core;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.TableVisitor;
import reports.Taula;

@SuppressWarnings("serial")
public abstract class Activity implements Serializable, Printable {
	
	private static Logger logger = LoggerFactory.getLogger("Activity");
	
	/**
	 * @uml.property name="name"
	 */
	protected String name;
	
	public final String getName() {
		return this.name;
	}
	
	/**
	 * @uml.property name="description"
	 */
	protected String description;
	
	/**
	 * An activity has a project
	 * 
	 * @uml.property name="project"
	 * @uml.associationEnd multiplicity="(1 1)"
	 *                     inverse="activities:core.Project"
	 */
	protected Project project;
	
	public final Project getProject() {
		return this.project;
	}
	
	/**
	 * Used to store initial, final and duration times
	 */
	protected Periode periode;
	
	public final Periode getPeriode() {
		
		return this.periode;
	}
	
	public final void setPeriode(final Periode pPeriode) {
		assert pPeriode != null;
		this.periode = pPeriode;
	}
	
	/**
	 * Activity is a abstract class for project and task This class is the
	 * component of the composite design We needed this pattern because we have
	 * to contain in a project, tasks and other projects This function starts
	 * the dates to null because we need it to control when a activity is not
	 * started, to assign both Finally it assign the level of the logger
	 */
	public Activity(final String pName, final String pDescription,
	        final Project pProject) {
		assert pName != null;
		assert pDescription != null;
		assert pProject != null;
		this.name = pName;
		this.description = pDescription;
		this.project = pProject;
		this.periode = new Periode(null, null);
	}
	
	/**
	 * It's used to update the dates and duration of the activity. At first we
	 * watch the dates state and we use the null state for determinate if is the
	 * first iteration in the activity, it is required to control the assignment
	 * of the start date. This along with the parameter "first" we control the
	 * initialization and update of the duration. Update the finish date with
	 * clock At last we call this function at the parent, if the activity have
	 * it
	 */
	protected final void updateActivity(final Object clock, 
			final boolean first) {
		assert clock != null;
		// If no start date defined
		if (this.periode.getDataInici() == null) {
			this.periode.setDataInici(((Clock) clock).getDate());
			this.periode.setDuration((long) 0);
		} else if (!first) {
			this.periode.incrementDuration(((Clock) clock).getUpdatePeriod());
		}
		this.periode.setDataFi(((Clock) clock).getDate());
		
		if (this.project != null) {
			this.project.updateActivity(clock, first);
		}
		logger.debug("Updated activity " + this.name);
	}
	
	public abstract void acceptTableVisitor(TableVisitor tableVisitor,
	        Taula table, Periode pPeriode);
}
