package reports;


public class Title extends ReportElement {

    /**
     * @uml.property  name="text"
     */
    private String text;
    
	public Title(String textValue){
	    this.text = textValue;
	}
	
	@Override
    public void accept(ReportVisitor visitor) {
        visitor.visitTitle(this);
    }
	
	public String getText(){
	    return this.text;
	}
	
}
