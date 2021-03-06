package reports;

import java.util.ArrayList;
import java.util.List;

import core.Activity;
import core.Interval;
import core.Periode;
import core.Project;
import core.Task;

/**
 * Class that implements the visit methods for the projectTable
 *
 */
public class TableProjectVisitor extends TableVisitor {
	
	@Override
	public void visitInterval(final Interval interval, 
			final Taula table, final Periode periode) {
	}
	
	@Override
    public final void visitProject(final Project project, 
    		final Taula table, final Periode periode) {
		assert project != null;
		assert table != null;
		assert periode != null;
		System.out.println(project);
		
		List<Object> projectArray = new ArrayList<Object>();
		assert projectArray != null;
		Periode periodeIntersection = project.getPeriode().intersect(periode);
		// If exists intersection
		if (periodeIntersection != null) {
			
			projectArray.add(project.getName());
			projectArray
			        .add(periodeIntersection.getDataIniciAsStringFormated());
			projectArray.add(periodeIntersection.getDataFiAsStringFormated());
			projectArray.add(periodeIntersection.getDurationAsStringFormated());
			table.afegeixFila((ArrayList<Object>) projectArray);
			
			List<Activity> rootProjects = project.getActivities();
			assert rootProjects != null: "There are no projects to report";
			for (Activity activity : rootProjects) {
				activity.acceptTableVisitor(this, table, periode);
			}
			
		}
		
	}
	
	@Override
	public void visitTask(final Task task, final Taula table, 
			final Periode periode) {
	}
	
}
