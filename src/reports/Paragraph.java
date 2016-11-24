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
	
	private boolean invariant(){
		if (this.text == null){
			return false;
		}
		return true;
	}
	
	public Paragraph(final String textValue) {
		assert textValue != null: "Paragraph text can't be null" ;
		this.text = textValue;
		assert invariant();
	}
	
	public final String getText() {
		assert invariant();
		return this.text;
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		assert visitor != null;
		visitor.visitParagraph(this);
		assert invariant();
	}
	
}
