package reports;

import java.util.List;

import core.Activity;
import core.Periode;
import core.Project;


public class ShortReport extends Report {
	private static final int UNO = 1;
	private static final int DOS = 2;
	private static final int TRES = 3;
	private static final int CUATRO = 4;
/**
 * Constructor that calls the super constructor and initialize  the 
 * specific elements of the short report
 *
 */	
	public ShortReport(final Periode periode, final Project root) {
		super("Short Report", periode, root);
		this.addElement(new Line());
		this.addElement(new Subtitle("Projectes Arrel"));
		Taula projectTable = createProjectTable(periode);
		this.addElement(projectTable);
		this.addElement(new Line());
		this.addElement(new Footer("Time Tracker v1.0"));
	}
	
	public final Taula createProjectTable(final Periode periode) {
		assert periode != null;
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
	
}
