package reports;

/**
 * Class to define the paragraphs structure and methods
 *
 */
public class Paragraph extends ReportElement {
	
	/**
	 * @uml.property name="text"
	 */
	private String text;
	
	public Paragraph(final String textValue) {
		assert textValue != null;
		this.text = textValue;
	}
	
	public final String getText() {
		return this.text;
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		assert visitor != null;
		visitor.visitParagraph(this);
	}
	
}
