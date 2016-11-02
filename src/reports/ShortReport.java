package reports;


public class ShortReport extends Report {
	public ShortReport(){
		super("Short Report");
		this.addElement(new Subtitle("Període"));
		this.addElement(new Taula(4,2));
		this.addElement(new Line());
		this.addElement(new Subtitle("Projectes Arrel"));
		this.addElement(new Taula(4,4));
		this.addElement(new Line());
	    this.addElement(new Footer("Time Tracker v1.0"));
	}
	
}
