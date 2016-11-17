package reports;

import core.*;


public class ShortReport extends Report {
	
	public ShortReport(Periode periode, Project root){
		super("Short Report",periode,root);
		this.addElement(new Line());
		this.addElement(new Subtitle("Projectes Arrel"));
		Taula projectTable = createProjectTable(periode);
		this.addElement(projectTable);
		this.addElement(new Line());
	    this.addElement(new Footer("Time Tracker v1.0"));
	}
	
	public Taula createProjectTable(Periode periode){
		Taula projectTable = new Taula(1,4);
		projectTable.setFirstRowHeader(true);
		projectTable.setPosicio(1, 1, "Project");
		projectTable.setPosicio(1, 2, "Data d'inici");
		projectTable.setPosicio(1, 3, "Data final");
		projectTable.setPosicio(1, 4, "Durada");
		
		TableProjectVisitor projectVisitor = new TableProjectVisitor();
		this.root.acceptTableVisitor(projectVisitor,projectTable,periode);
		
		return projectTable;
		
	}
	
}
