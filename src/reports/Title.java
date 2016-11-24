package reports;

public class Title extends ReportElement {
	
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
	
	public Title(final String textValue) {
		assert textValue != null: "Title can't be null";
		this.text = textValue;
		assert invariant();
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		assert visitor != null;
		visitor.visitTitle(this);
		assert invariant();
	}
	
	public final String getText() {
		assert invariant();
		return this.text;
	}
	
}
