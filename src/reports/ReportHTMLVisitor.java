package reports;

import java.io.PrintWriter;
import java.util.Collection;


public class ReportHTMLVisitor extends ReportVisitor {

    private PaginaWeb webPage;
    private PrintWriter write;

    public ReportHTMLVisitor(PrintWriter writer){
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
        this.webPage.escriuPagina();
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
        //Collection<?> colle = table.getTaula();
        //this.webPage.afegeixTaula(colle, true, true);

    }



}
