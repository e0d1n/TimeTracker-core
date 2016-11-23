package reports;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import core.Periode;
import core.Project;

/**
 * @author erodrgal
 *
 */
public abstract class Report {
	private static final int UNO = 1;
	private static final int DOS = 2;
	private static final int TRES = 3;
	private static final int CUATRO = 4;
	
	/**
	 * @uml.property name="reportElements"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     inverse="report:reports.ReportElement"
	 */
	protected List<ReportElement> reportElements;
	
	protected Periode userPeriode;
	protected Project root;
	
	
	protected final void addElement(final ReportElement element) {
		reportElements.add(element);
	}
	
	// List<Activity> activities, Date startDate, Date finishDate
	/**
	 * Abstract constructor that initialize the common part of the report
	 * @param title
	 * @param periode
	 * @param pRoot
	 */
	public Report(final String title, 
			final Periode periode, final Project pRoot) {
		this.userPeriode = periode;
		this.root = pRoot;
		this.reportElements = new java.util.ArrayList<ReportElement>();
		this.reportElements.add(new Line());
		this.reportElements.add(new Title(title));
		this.reportElements.add(new Line());
		this.addElement(new Subtitle("Per’ode"));
		Taula tableUserPeriode = createUserPeriodeTable(periode);
		
		this.reportElements.add(tableUserPeriode);
	}
	
	public final Taula createUserPeriodeTable(final Periode periode) {
		Taula tableUserPeriode = new Taula(CUATRO, DOS);
		tableUserPeriode.setFirstColumnHeader(true);
		tableUserPeriode.setFirstRowHeader(true);
		tableUserPeriode.setPosicio(UNO, DOS, "Data");
		tableUserPeriode.setPosicio(DOS, UNO, "Desde");
		tableUserPeriode.setPosicio(TRES, UNO, "Fins a");
		tableUserPeriode.setPosicio(CUATRO, UNO, "Data de generaci—");
		// Data
		tableUserPeriode.setPosicio(DOS, DOS,
		        periode.getDataIniciAsStringFormated());
		tableUserPeriode.setPosicio(TRES, DOS,
		        periode.getDataFiAsStringFormated());
		tableUserPeriode.setPosicio(CUATRO, DOS,
		        periode.getDateAsStringFormated(new Date()));
		
		return tableUserPeriode;
		
	}
	
	/**
	 * @uml.property name="startDate"
	 */
	protected Date startDate;
	
	/**
	 * @uml.property name="finishDate"
	 */
	protected Date finishDate;
	
	/**
     */
	public final void printReport(final ReportVisitor visitor)
	        throws IOException {
		
		for (ReportElement element : reportElements) {
			element.accept(visitor);
		}
		visitor.writeAndCloseFile();
	}
	
}
