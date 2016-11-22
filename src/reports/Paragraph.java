package reports;

public class Paragraph extends ReportElement {
	
	/**
	 * @uml.property name="text"
	 */
	private String text;
	
	public Paragraph(final String textValue) {
		this.text = textValue;
	}
	
	public final String getText() {
		return this.text;
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		visitor.visitParagraph(this);
	}
	
}
