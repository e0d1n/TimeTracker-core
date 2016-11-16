package reports;

import java.util.ArrayList;
import java.util.List;

import core.*;


public class TableProjectVisitor extends TableVisitor {

	@Override
	public void visitInterval(Interval interval, Taula table, Periode periode) {
	}

	@Override
	public void visitProject(Project project, Taula table, Periode periode) {
		System.out.println(project);
	
		List<Object> projectArray = new ArrayList<Object>();		
		Periode periodeIntersection = project.getPeriode().intersect(periode);
		if (periodeIntersection != null){
			
			projectArray.add(project.getName());
			projectArray.add(periodeIntersection.getDataIniciAsStringFormated());
			projectArray.add(periodeIntersection.getDataFiAsStringFormated());
			projectArray.add(periodeIntersection.getDurationAsStringFormated());
			table.afegeixFila((ArrayList) projectArray);
			
			List<Activity> subprojects = project.getActivities();
			
			for ( Activity activity : subprojects ){
				activity.acceptTableVisitor(this, table, periode);
			}
		}
		
	}

	@Override
	public void visitTask(Task task, Taula table, Periode periode) {
	}

}
