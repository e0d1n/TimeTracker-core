package reports;

import java.util.ArrayList;
import java.util.List;

import core.Activity;
import core.Interval;
import core.Periode;
import core.Project;
import core.Task;

/**
 * Class that implements the visit methods for the taskTable
 *
 */
public class TableTaskVisitor extends TableVisitor {
	
	@Override
	public void visitInterval(final Interval interval,
			final Taula table, final Periode periode) {
	}
	
	@Override
    public final void visitProject(final Project project, 
    		final Taula table, final Periode periode) {
		
		List<Activity> subprojects = project.getActivities();
		Periode periodeIntersection = project.getPeriode().intersect(periode);
		if (periodeIntersection != null) {
			for (Activity activity : subprojects) {
				activity.acceptTableVisitor(this, table, periode);
			}
		}
	}
	
	@Override
    public final void visitTask(final Task task, final Taula table, 
    		final Periode periode) {
		System.out.println(task);
		List<Object> taskArray = new ArrayList<Object>();
		Periode periodeIntersection = task.getPeriode().intersect(periode);
		if (periodeIntersection != null) {
			taskArray.add(task.getProject().getName());
			taskArray.add(task.getName());
			taskArray.add(periodeIntersection.getDataIniciAsStringFormated());
			taskArray.add(periodeIntersection.getDataFiAsStringFormated());
			taskArray.add(periodeIntersection.getDurationAsStringFormated());
			table.afegeixFila((ArrayList<Object>) taskArray);
		}
	}
	
}
