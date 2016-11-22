package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;

public class ReportTextVisitor extends ReportVisitor {
	
	private PrintWriter writer;
	private static final int MAXLENGTH = 102;
	
	private String wrapString(final String input) {
		return WordUtils.wrap(input, MAXLENGTH, null, false);
		
	}
	
	public ReportTextVisitor(final String filename) throws IOException {
		this.writer = new PrintWriter(filename);
	}
	
	@Override
    public final void visitTitle(final Title title) {
		
		this.writer.println(title.getText());
		
	}
	
	@Override
    public final void visitLine(final Line line) {
		
		for (int i = 0; i < ReportTextVisitor.MAXLENGTH; i++) {
			this.writer.print("-");
		}
		this.writer.println();
	}
	
	@Override
    public final void visitFooter(final Footer footer) {
		
		this.writer.println(footer.getText());
		
	}
	
	@Override
    public final void visitSubtitle(final Subtitle subtitle) {
		
		this.writer.println(subtitle.getText());
		
	}
	
	@Override
    public final void visitParagraph(final Paragraph paragraph) {
		String paragraphText = paragraph.getText();
		this.writer.println(wrapString(paragraphText));
		
	}
	
	
    @Override
    public final void visitTable(final Taula table) {
		
		ArrayList tableArray = table.getTaula();
		for (Object fila : tableArray) {
			for (Object columna : (ArrayList) fila) {
				if (columna == null) {
					this.writer.print("      ");
				} else {
					this.writer.print(columna + "  ");
				}
			}
			this.writer.println();
		}
	}
	
	@Override
    public final void writeAndCloseFile() {
		writer.close();
	}
}
