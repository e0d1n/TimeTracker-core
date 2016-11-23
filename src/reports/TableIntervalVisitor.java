package reports;

import java.util.ArrayList;
import java.util.List;

import core.Activity;
import core.Interval;
import core.Periode;
import core.Project;
import core.Task;

/**
 * Class that implements the visit methods for the intervalTable
 *
 */
public class TableIntervalVisitor extends TableVisitor {
	
	private static int counter;
	
	@Override
    public final void visitInterval(final Interval interval,
    		final Taula table, final Periode periode) {
		System.out.println(interval);
		
		List<Object> intervalArray = new ArrayList<Object>();
		
		Periode periodeIntersection = interval.getPeriode().intersect(periode);
		
		if (periodeIntersection != null) {
			intervalArray.add(interval.getTask().getName());
			intervalArray.add(counter);
			intervalArray.add(periodeIntersection
			        .getDataIniciAsStringFormated());
			intervalArray.add(periodeIntersection.getDataFiAsStringFormated());
			intervalArray
			        .add(periodeIntersection.getDurationAsStringFormated());
			table.afegeixFila((ArrayList<Object>) intervalArray);
		}
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
    public final void visitTask(final Task task, 
    		final Taula table, final Periode periode) {
		List<Interval> intervals = task.getIntervals();
		counter = 1;
		Periode periodeIntersection = task.getPeriode().intersect(periode);
		if (periodeIntersection != null) {
			for (Interval interval : intervals) {
				interval.acceptTableVisitor(this, table, periode);
				counter++;
			}
		}
		
	}
	
}
