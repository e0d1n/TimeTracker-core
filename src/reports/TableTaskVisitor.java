package reports;

import java.util.ArrayList;
import java.util.List;

import core.*;


public class TableTaskVisitor extends TableVisitor {

	@Override
	public void visitInterval(Interval interval, Taula table, Periode periode) {
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
		System.out.println(task);
		List<Object> taskArray = new ArrayList<Object>();
		Periode periodeIntersection = task.getPeriode().intersect(periode);
		if (periodeIntersection != null){
			taskArray.add(task.getProject().getName());
			taskArray.add(task.getName());
			taskArray.add(periodeIntersection.getDataIniciAsStringFormated());
			taskArray.add(periodeIntersection.getDataFiAsStringFormated());
			taskArray.add(periodeIntersection.getDurationAsStringFormated());
			table.afegeixFila((ArrayList) taskArray);
		}
	}

}
