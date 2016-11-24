package reports;

public class Footer extends ReportElement {
	
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
	
	public Footer(final String textValue) {
		assert textValue != null: "Footer text can't be null" ;
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
		visitor.visitFooter(this);
		assert invariant();
	}
	
}
