package reports;


public class Subtitle extends ReportElement {

    /**
     * @uml.property  name="text"
     */
    private String text;
    
    public Subtitle(String textValue){
	   this.text = textValue;   
	}
    
    public String getText(){
        return this.text;
    }
    
    
    @Override
	public void accept(ReportVisitor visitor) {
		visitor.visitSubtitle(this);
	}

	
}
