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
		assert filename != null;
		writer = new PrintWriter(filename);
		this.webPage = new PaginaWeb(writer);
	}
	
	@Override
    public final void visitTitle(final Title title) {
		assert title != null;
		this.webPage.afegeixHeader(title.getText(), 1, true);
		
	}
	
	@Override
    public final void visitLine(final Line line) {
		assert line != null;
		this.webPage.afegeixLiniaSeparacio();
		
	}
	
	@Override
    public final void visitFooter(final Footer footer) {
		assert footer != null;
		this.webPage.afegeixTextNormal(footer.getText());
		
	}
	
	@Override
    public final void visitSubtitle(final Subtitle subtitle) {
		assert subtitle != null;
		this.webPage.afegeixHeader(subtitle.getText(), TRES, false);
		
	}
	
	@Override
    public final void visitParagraph(final Paragraph paragraph) {
		assert paragraph != null;
		this.webPage.afegeixTextNormal(paragraph.getText());
		
	}
	
	@Override
    public final void visitTable(final Taula table) {
		assert table != null;
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
