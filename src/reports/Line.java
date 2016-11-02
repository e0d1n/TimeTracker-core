package reports;


public class Line extends ReportElement {

	@Override
	public void accept(ReportVisitor visitor) {
		visitor.visitLine(this);
	}


}
