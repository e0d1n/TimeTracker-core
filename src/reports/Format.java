package reports;
/**
 * Abstract class that declares the visit methods of Report
 *
 */
public abstract class Format {
	
	/**
     */
	public abstract void visitTitle(Title title);
	
	/**
     */
	public abstract void visitLine(Line line);
	
	/**
     */
	public abstract void visitFooter(Footer footer);
	
	/**
     */
	public abstract void visitSubtitle(Subtitle subtitle);
	
	/**
     */
	public abstract void visitParagraph(Paragraph paragraph);
	
	/**
     */
	public abstract void visitTable(Taula table);
	
	/**
      */
	public abstract void writeAndCloseFile();
	
}
