package reports;

public class Line extends ReportElement {
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		visitor.visitLine(this);
	}
	
}
