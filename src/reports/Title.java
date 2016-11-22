package reports;

public class Title extends ReportElement {
	
	/**
	 * @uml.property name="text"
	 */
	private String text;
	
	public Title(final String textValue) {
		this.text = textValue;
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		visitor.visitTitle(this);
	}
	
	public final String getText() {
		return this.text;
	}
	
}
