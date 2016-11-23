package reports;

public class Subtitle extends ReportElement {
	
	/**
	 * @uml.property name="text"
	 */
	private String text;
	
	public Subtitle(final String textValue) {
		assert textValue != null;
		this.text = textValue;
	}
	
	public final String getText() {
		return this.text;
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		assert visitor != null;
		visitor.visitSubtitle(this);
	}
	
}
