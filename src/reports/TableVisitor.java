package reports;

import core.*;


public abstract class TableVisitor {
	
	public abstract void visitProject(Project project, Taula table, Periode periode);
	
	public abstract void visitTask(Task task, Taula table, Periode periode);
	
	public abstract void visitInterval(Interval interval, Taula table, Periode periode);

}
