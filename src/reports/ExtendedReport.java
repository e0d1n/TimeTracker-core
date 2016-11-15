package reports;


public class ExtendedReport extends Report {
	public ExtendedReport(){
		super("Extended Report");
		this.addElement(new Subtitle("Període"));
        this.addElement(new Taula(4,2));
        this.addElement(new Line());
        this.addElement(new Subtitle("Projectes Arrel"));
        this.addElement(new Taula(5,4)); 
        this.addElement(new Line());
        this.addElement(new Subtitle("Subprojectes"));
        this.addElement(new Paragraph("S’inclouen en la següent taula només els subprojectes que tinguin alguna tasca amb algun interval dins del període."));
        this.addElement(new Taula(5,5)); 
        this.addElement(new Line());
        this.addElement(new Subtitle("Tasques"));
        this.addElement(new Paragraph("S’inclouen en la següent taula la durada de totes tasques i el projecte al qual pertanyen."));
        this.addElement(new Taula(5,6)); 
        this.addElement(new Line());
        this.addElement(new Subtitle("Intervals"));
        this.addElement(new Paragraph("S’inclouen en la següent taula el temps d’inici, final i durada de tots els intervals entre la data inicial i final especificades, i la tasca i projecte al qual pertanyen."));
        this.addElement(new Taula(6,8)); 
        this.addElement(new Line());
        this.addElement(new Footer("Time Tracker v1.0"));
        
	}
}
