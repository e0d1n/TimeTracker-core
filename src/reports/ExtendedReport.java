package reports;

import core.*;


public class ExtendedReport extends Report {
	public ExtendedReport(Periode periode, Activity root){
		super("Extended Report", periode, root);
        this.addElement(new Line());
        this.addElement(new Subtitle("Projectes Arrel"));
        Taula projectTable = createProjectTable(periode);
        this.addElement(projectTable); 
        this.addElement(new Line());
        //this.addElement(new Subtitle("Subprojectes"));
        //this.addElement(new Paragraph("S'inclouen en la següent taula només els subprojectes que tinguin alguna tasca amb algun interval dins del període."));
        //this.addElement(new Taula(5,5)); 
        //this.addElement(new Line());
        this.addElement(new Subtitle("Tasques"));
        this.addElement(new Paragraph("S'inclouen en la següent taula la durada de totes tasques i el projecte al qual pertanyen."));
        Taula taskTable = createTaskTable(periode);
        this.addElement(taskTable); 
        this.addElement(new Line());
        this.addElement(new Subtitle("Intervals"));
        this.addElement(new Paragraph("S'inclouen en la següent taula el temps d'inici, final i durada de tots els intervals entre la data inicial i final especificades, i la tasca i projecte al qual pertanyen."));
        Taula intervalTable = createIntervalTable(periode);
        this.addElement(intervalTable); 
        this.addElement(new Line());
        this.addElement(new Footer("Time Tracker v1.0"));
        
	}
	
	
	public Taula createProjectTable(Periode periode){
		Taula projectTable = new Taula(1,4);
		projectTable.setPosicio(1, 1, "Project");
		projectTable.setPosicio(1, 2, "Data d'inici");
		projectTable.setPosicio(1, 3, "Data final");
		projectTable.setPosicio(1, 4, "Durada");
		
		TableProjectVisitor projectVisitor = new TableProjectVisitor();
		this.root.acceptTableVisitor(projectVisitor,projectTable,periode);
		
		return projectTable;
		
	}
	
	public Taula createTaskTable(Periode periode){
		Taula taskTable = new Taula(1,5);
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
