package reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import core.Activity;

public abstract class Report {

    /**
     * @uml.property name="reportElements"
     * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
     *                     inverse="report:reports.ReportElement"
     */
    protected List<ReportElement> reportElements;

    protected void addElement(ReportElement element) {
        reportElements.add(element);
    }

    // List<Activity> activities, Date startDate, Date finishDate
    public Report(String title) {
        this.reportElements = new java.util.ArrayList<ReportElement>();
        this.reportElements.add(new Line());
        this.reportElements.add(new Title(title));
        this.reportElements.add(new Line());
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
    public void printReport(PrintWriter writer, ReportVisitor visitor) throws IOException {

        for (ReportElement element : reportElements) {
            element.accept(visitor);
        }
    }
}
