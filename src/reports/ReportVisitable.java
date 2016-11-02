package reports;


public interface ReportVisitable {
	public void accept(ReportVisitor visitor);
}
