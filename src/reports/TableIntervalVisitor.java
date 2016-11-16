package reports;

import java.util.ArrayList;
import java.util.List;

import core.*;


public class TableIntervalVisitor extends TableVisitor {

	static int counter;
	
	@Override
	public void visitInterval(Interval interval, Taula table, Periode periode) {
		System.out.println(interval);
		
		List<Object> intervalArray = new ArrayList<Object>();
		
		Periode periodeIntersection = interval.getPeriode().intersect(periode);
		
		if (periodeIntersection != null){
			intervalArray.add(interval.getTask().getName());
			intervalArray.add(counter);
			intervalArray.add(periodeIntersection.getDataInici());
			intervalArray.add(periodeIntersection.getDataFi());
			intervalArray.add(periodeIntersection.getDuration());
			table.afegeixFila((ArrayList) intervalArray);
		}
	}

	@Override
	public void visitProject(Project project, Taula table, Periode periode) {

		List<Activity> subprojects = project.getActivities();
		Periode periodeIntersection = project.getPeriode().intersect(periode);
		if (periodeIntersection != null){
			for ( Activity activity : subprojects ){
				activity.acceptTableVisitor(this,table, periode);
			}
		}
	}

	@Override
	public void visitTask(Task task, Taula table, Periode periode) {
		List<Interval> intervals = task.getIntervals();
		counter = 1;
		Periode periodeIntersection = task.getPeriode().intersect(periode);
		if (periodeIntersection != null){
			for ( Interval interval : intervals ){
				interval.acceptTableVisitor(this, table, periode);
				counter ++;
			}
		}
		
	}

}
