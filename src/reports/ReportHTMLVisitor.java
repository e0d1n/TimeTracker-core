package reports;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;


public class ReportHTMLVisitor extends ReportVisitor {

    private PaginaWeb webPage;
    private PrintWriter writer;

    public ReportHTMLVisitor(String filename) throws FileNotFoundException{
    	writer = new PrintWriter(filename);
        this.webPage = new PaginaWeb(writer);
    }

    @Override
    public void visitTitle(Title title) {
        this.webPage.afegeixHeader(title.getText(), 1, true);

    }

    @Override
    public void visitLine(Line line) {
        this.webPage.afegeixLiniaSeparacio();

    }

    @Override
    public void visitFooter(Footer footer) {
        this.webPage.afegeixTextNormal(footer.getText());
        
    }

    @Override
    public void visitSubtitle(Subtitle subtitle) {
        this.webPage.afegeixHeader(subtitle.getText(), 3, false);

    }

    @Override
    public void visitParagraph(Paragraph paragraph) {
        this.webPage.afegeixTextNormal(paragraph.getText());

    }

    @Override
    public void visitTable(Taula table) {
    	Collection tableCollection = table.getTaula();
        this.webPage.afegeixTaula(tableCollection, table.getFirstRowHeader(), table.getFirstColumnHeader());

    }

    @Override
    public void writeAndCloseFile(){
    	this.webPage.escriuPagina();
    	writer.close();
    }

}
