package reports;

import java.util.List;

import core.*;


public class ExtendedReport extends Report {
	public ExtendedReport(Periode periode, Project root){
		super("Extended Report", periode, root);
        this.addElement(new Line());
        this.addElement(new Subtitle("Projectes Arrel"));
        Taula projectTable = createProjectTable(periode);
        this.addElement(projectTable); 
        this.addElement(new Line());
        //this.addElement(new Subtitle("Subprojectes"));
        //this.addElement(new Paragraph("S'inclouen en la seg�ent taula nom�s els subprojectes que tinguin alguna tasca amb algun interval dins del per�ode."));
        //this.addElement(new Taula(5,5)); 
        //this.addElement(new Line());
        this.addElement(new Subtitle("Tasques"));
        this.addElement(new Paragraph("S'inclouen en la seg�ent taula la durada de totes tasques i el projecte al qual pertanyen."));
        Taula taskTable = createTaskTable(periode);
        this.addElement(taskTable); 
        this.addElement(new Line());
        this.addElement(new Subtitle("Intervals"));
        this.addElement(new Paragraph("S'inclouen en la seg�ent taula el temps d'inici, final i durada de tots els intervals entre la data inicial i final especificades, i la tasca i projecte al qual pertanyen."));
        Taula intervalTable = createIntervalTable(periode);
        this.addElement(intervalTable); 
        this.addElement(new Line());
        this.addElement(new Footer("Time Tracker v1.0"));
        
        /*
        Taula subprojectTable = createSubProjectTable(periode);
        this.addElement(subprojectTable);
        */
        
	}
	
	
	public Taula createProjectTable(Periode periode){
		Taula projectTable = new Taula(1,4);
		projectTable.setFirstRowHeader(true);
		projectTable.setPosicio(1, 1, "Project");
		projectTable.setPosicio(1, 2, "Data d'inici");
		projectTable.setPosicio(1, 3, "Data final");
		projectTable.setPosicio(1, 4, "Durada");
		
		TableProjectVisitor projectVisitor = new TableProjectVisitor();
		
		List<Activity> baseProjects = this.root.getActivities();
		
		for ( Activity activity : baseProjects ){
			activity.acceptTableVisitor(projectVisitor,projectTable,periode);
		}
		
		//this.root.acceptTableVisitor(projectVisitor,projectTable,periode);
		
		return projectTable;
		
	}
	
	public Taula createSubProjectTable(Periode periode){
		Taula subProjectTable = new Taula(1,4);
		subProjectTable.setFirstRowHeader(true);
		subProjectTable.setPosicio(1, 1, "Project");
		subProjectTable.setPosicio(1, 2, "Data d'inici");
		subProjectTable.setPosicio(1, 3, "Data final");
		subProjectTable.setPosicio(1, 4, "Durada");
		
		TableSubProjectVisitor subProjectVisitor = new TableSubProjectVisitor();

		List<Activity> baseProjects = this.root.getActivities();
		
		for ( Activity activity : baseProjects ){
			activity.acceptTableVisitor(subProjectVisitor,subProjectTable,periode);
		}
		
		return subProjectTable;
	}
	
	public Taula createTaskTable(Periode periode){
		Taula taskTable = new Taula(1,5);
		taskTable.setFirstRowHeader(true);
		taskTable.setPosicio(1, 1, "Nom Projecte");
		taskTable.setPosicio(1, 2, "Tasca");
		taskTable.setPosicio(1, 3, "Data d'inici");
		taskTable.setPosicio(1, 4, "Data final");
		taskTable.setPosicio(1, 5, "Durada");
		
		TableTaskVisitor taskVisitor = new TableTaskVisitor();
		this.root.acceptTableVisitor(taskVisitor,taskTable,periode);
		
		return taskTable;
		
	}
	
	public Taula createIntervalTable(Periode periode){
		Taula intervalTable = new Taula(1,5);
		intervalTable.setFirstRowHeader(true);
		intervalTable.setPosicio(1, 1, "Tasca");
		intervalTable.setPosicio(1, 2, "Interval");
		intervalTable.setPosicio(1, 3, "Data d'inici");
		intervalTable.setPosicio(1, 4, "Data final");
		intervalTable.setPosicio(1, 5, "Durada");
		
		TableIntervalVisitor taskVisitor = new TableIntervalVisitor();
		this.root.acceptTableVisitor(taskVisitor,intervalTable,periode);
		
		return intervalTable;
		
	}
	
	
	
}