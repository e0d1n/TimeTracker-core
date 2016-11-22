package reports;

public interface ReportVisitable {
	void accept(ReportVisitor visitor);
}
