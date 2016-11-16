package reports;


public class ShortReport extends Report {
	public ShortReport(){
		super("Short Report");
		this.addElement(new Subtitle("Per’ode"));
		Taula tableOne = new Taula(4,2);
		this.addElement(tableOne);
		this.addElement(new Line());
		this.addElement(new Subtitle("Projectes Arrel"));
		Taula tableTwo = new Taula(4,4);
		this.addElement(tableTwo);
		this.addElement(new Line());
	    this.addElement(new Footer("Time Tracker v1.0"));
	}
	
}
