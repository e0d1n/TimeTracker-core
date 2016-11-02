package reports;


public class Paragraph extends ReportElement {

    /**
     * @uml.property  name="text"
     */
    private String text;
    
	public Paragraph(String textValue){
	    this.text = textValue;
	}
	
	public String getText(){
	    return this.text;
	}
	
	@Override
    public void accept(ReportVisitor visitor) {
        visitor.visitParagraph(this);
    }
    
	
}
