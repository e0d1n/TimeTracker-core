package reports;

public class Subtitle extends ReportElement {
	
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
	
	public Subtitle(final String textValue) {
		assert textValue != null: "Subtitle text can't be null";
		this.text = textValue;
		assert invariant();
	}
	
	public final String getText() {
		assert invariant();
		return this.text;
	}
	
	@Override
    public final void accept(final Format visitor) {
		assert visitor != null;
		visitor.visitSubtitle(this);
		assert invariant();
	}
	
}
