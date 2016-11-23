package reports;

public class Title extends ReportElement {
	
	/**
	 * @uml.property name="text"
	 */
	private String text;
	
	public Title(final String textValue) {
		assert textValue != null;
		this.text = textValue;
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		assert visitor != null;
		visitor.visitTitle(this);
	}
	
	public final String getText() {
		return this.text;
	}
	
}
