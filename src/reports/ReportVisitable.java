package reports;

/**
 * interface that declares the accept visitor for the reports
 *
 */
public interface ReportVisitable {
	void accept(ReportVisitor visitor);
}
