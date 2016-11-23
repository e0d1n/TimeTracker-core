package reports;

import java.util.List;

import core.Activity;
import core.Periode;
import core.Project;

public class ExtendedReport extends Report {
	private static final int UNO = 1;
	private static final int DOS = 2;
	private static final int TRES = 3;
	private static final int CUATRO = 4;
	private static final int CINCO = 5;
	
	public ExtendedReport(final Periode periode, final Project root) {
		super("Extended Report", periode, root);
		this.addElement(new Line());
		this.addElement(new Subtitle("Projectes Arrel"));
		Taula projectTable = createProjectTable(periode);
		this.addElement(projectTable);
		this.addElement(new Line());
		this.addElement(new Subtitle("Subprojectes"));
		this.addElement(new
		Paragraph("S'inclouen en la següent taula només els subprojectes" 
				+ "que tinguin alguna tasca amb algun interval dins del període."));
		Taula subProjectTable = createSubProjectTable(periode);
		this.addElement(subProjectTable);
		this.addElement(new Line());
		this.addElement(new Subtitle("Tasques"));
		this.addElement(new Paragraph(
		        "S'inclouen en la següent taula la durada de" 
			  + "totes tasques i el projecte al qual pertanyen."));
		Taula taskTable = createTaskTable(periode);
		this.addElement(taskTable);
		this.addElement(new Line());
		this.addElement(new Subtitle("Intervals"));
		this.addElement(new Paragraph(
		        "S'inclouen en la següent taula el temps d'inici, final"
				+ "i durada de tots els intervals entre la data inicial"
		        + " i final especificades, i la tasca i projecte al qual"
				+ "pertanyen."));
		Taula intervalTable = createIntervalTable(periode);
		this.addElement(intervalTable);
		this.addElement(new Line());
		this.addElement(new Footer("Time Tracker v1.0"));
		
		/*
		 * Taula subprojectTable = createSubProjectTable(periode);
		 * this.addElement(subprojectTable);
		 */
		
	}
	
	public final Taula createProjectTable(final Periode periode) {
		Taula projectTable = new Taula(UNO, CUATRO);
		projectTable.setFirstRowHeader(true);
		projectTable.setPosicio(UNO, UNO, "Project");
		projectTable.setPosicio(UNO, DOS, "Data d'inici");
		projectTable.setPosicio(UNO, TRES, "Data final");
		projectTable.setPosicio(UNO, CUATRO, "Durada");
		
		TableProjectVisitor projectVisitor = new TableProjectVisitor();
		
		List<Activity> baseProjects = this.root.getActivities();
		
		for (Activity activity : baseProjects) {
			activity.acceptTableVisitor(projectVisitor, projectTable, periode);
		}
		
		return projectTable;
		
	}
	
	public final Taula createSubProjectTable(final Periode periode) {
		Taula subprojectTable = new Taula(UNO, CUATRO);
		subprojectTable.setFirstRowHeader(true);
		subprojectTable.setPosicio(UNO, UNO, "Project");
		subprojectTable.setPosicio(UNO, DOS, "Data d'inici");
		subprojectTable.setPosicio(UNO, TRES, "Data final");
		subprojectTable.setPosicio(UNO, CUATRO, "Durada");
		
		TableSubProjectVisitor subProjectVisitor = new TableSubProjectVisitor();
		
		List<Activity> baseProjects = this.root.getActivities();
		
		for (Activity activity : baseProjects) {
			activity.acceptTableVisitor(subProjectVisitor, subprojectTable, periode);
		}
		
		return subprojectTable;
	}
	
	public final Taula createTaskTable(final Periode periode) {
		Taula taskTable = new Taula(UNO, CINCO);
		taskTable.setFirstRowHeader(true);
		taskTable.setPosicio(UNO, UNO, "Nom Projecte");
		taskTable.setPosicio(UNO, DOS, "Tasca");
		taskTable.setPosicio(UNO, TRES, "Data d'inici");
		taskTable.setPosicio(UNO, CUATRO, "Data final");
		taskTable.setPosicio(UNO, CINCO, "Durada");
		
		TableTaskVisitor taskVisitor = new TableTaskVisitor();
		this.root.acceptTableVisitor(taskVisitor, taskTable, periode);
		
		return taskTable;
		
	}
	
	public final Taula createIntervalTable(final Periode periode) {
		Taula intervalTable = new Taula(UNO, CINCO);
		intervalTable.setFirstRowHeader(true);
		intervalTable.setPosicio(UNO, UNO, "Tasca");
		intervalTable.setPosicio(UNO, DOS, "Interval");
		intervalTable.setPosicio(UNO, TRES, "Data d'inici");
		intervalTable.setPosicio(UNO, CUATRO, "Data final");
		intervalTable.setPosicio(UNO, CINCO, "Durada");
		
		TableIntervalVisitor taskVisitor = new TableIntervalVisitor();
		this.root.acceptTableVisitor(taskVisitor, intervalTable, periode);
		
		return intervalTable;
		
	}
	
}
