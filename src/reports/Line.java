package reports;

/**
 * Class to define the line accept
 *
 */
public class Line extends ReportElement {
	
	@Override
    public final void accept(final Format visitor) {
		assert visitor != null;
		visitor.visitLine(this);
	}
	
}
