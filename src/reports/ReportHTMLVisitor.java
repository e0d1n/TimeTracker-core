package reports;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
/**
 * Class that declares the visit methods for html reports
 *
 */
public class ReportHTMLVisitor extends ReportVisitor {
	
	private PaginaWeb webPage;
	private PrintWriter writer;
	private static final int TRES = 3;
	public ReportHTMLVisitor(final String filename)
			throws FileNotFoundException {
		writer = new PrintWriter(filename);
		this.webPage = new PaginaWeb(writer);
	}
	
	@Override
    public final void visitTitle(final Title title) {
		this.webPage.afegeixHeader(title.getText(), 1, true);
		
	}
	
	@Override
    public final void visitLine(final Line line) {
		this.webPage.afegeixLiniaSeparacio();
		
	}
	
	@Override
    public final void visitFooter(final Footer footer) {
		this.webPage.afegeixTextNormal(footer.getText());
		
	}
	
	@Override
    public final void visitSubtitle(final Subtitle subtitle) {
		this.webPage.afegeixHeader(subtitle.getText(), TRES, false);
		
	}
	
	@Override
    public final void visitParagraph(final Paragraph paragraph) {
		this.webPage.afegeixTextNormal(paragraph.getText());
		
	}
	
	@Override
    public final void visitTable(final Taula table) {
		Collection tableCollection = table.getTaula();
		this.webPage.afegeixTaula(tableCollection, table.getFirstRowHeader(),
		        table.getFirstColumnHeader());
		
	}
	
	@Override
    public final void writeAndCloseFile() {
		this.webPage.escriuPagina();
		writer.close();
	}
	
}
