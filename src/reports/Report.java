package reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Date;

import core.*;

public abstract class Report {

    /**
     * @uml.property name="reportElements"
     * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
     *                     inverse="report:reports.ReportElement"
     */
    protected List<ReportElement> reportElements;
    
    protected Periode userPeriode;
    protected Project root;

    protected void addElement(ReportElement element) {
        reportElements.add(element);
    }

    // List<Activity> activities, Date startDate, Date finishDate
    public Report(String title, Periode periode, Project root) {
    	this.userPeriode = periode;
    	this.root = root;
        this.reportElements = new java.util.ArrayList<ReportElement>();
        this.reportElements.add(new Line());
        this.reportElements.add(new Title(title));
        this.reportElements.add(new Line());
        this.addElement(new Subtitle("Per’ode"));
        Taula tableUserPeriode = createUserPeriodeTable(periode);
        
		this.reportElements.add(tableUserPeriode);
    }
    
    public Taula createUserPeriodeTable(Periode periode){
        Taula tableUserPeriode = new Taula(4,2);
        tableUserPeriode.setFirstColumnHeader(true);
        tableUserPeriode.setFirstRowHeader(true);
        tableUserPeriode.setPosicio(1, 2, "Data");
        tableUserPeriode.setPosicio(2, 1, "Desde");
        tableUserPeriode.setPosicio(3, 1, "Fins a");
        tableUserPeriode.setPosicio(4, 1, "Data de generaci—");
        // Data
        tableUserPeriode.setPosicio(2, 2, periode.getDataIniciAsStringFormated());
        tableUserPeriode.setPosicio(3, 2, periode.getDataFiAsStringFormated());
        tableUserPeriode.setPosicio(4, 2, periode.getDateAsStringFormated(new Date()));

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
    public void printReport(ReportVisitor visitor) throws IOException {

        for (ReportElement element : reportElements) {
            element.accept(visitor);
        }
        visitor.writeAndCloseFile();
    }

}
