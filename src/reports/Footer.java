package reports;

import core.Printer;


public class Footer extends ReportElement {

    /**
     * @uml.property  name="text"
     */
    private String text;
    
    public Footer(String textValue){
        this.text = textValue;
    }
    
    public String getText(){ 
        return this.text;
    }
    
    @Override
	public void accept(ReportVisitor visitor) {
		visitor.visitFooter(this);
	}
	
	

}
