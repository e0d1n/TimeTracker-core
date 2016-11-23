package reports;

import core.Interval;
import core.Periode;
import core.Project;
import core.Task;

/**
 * Abstract class that declares the visit methods of tables
 *
 */
public abstract class TableVisitor {
	
	public abstract void visitProject(Project project, Taula table,
	        Periode periode);
	
	public abstract void visitTask(Task task, Taula table, Periode periode);
	
	public abstract void visitInterval(Interval interval, Taula table,
	        Periode periode);
	
}
