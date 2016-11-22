package reports;

public class Footer extends ReportElement {
	
	/**
	 * @uml.property name="text"
	 */
	private String text;
	
	public Footer(final String textValue) {
		this.text = textValue;
	}
	
	public final String getText() {
		return this.text;
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		visitor.visitFooter(this);
	}
	
}
