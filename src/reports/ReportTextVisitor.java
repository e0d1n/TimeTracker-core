package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Class that declares the visit methods for text reports
 *
 */
public class ReportTextVisitor extends ReportVisitor {
	
	private PrintWriter writer;
	private static final int MAXLENGTH = 102;
	
	private String wrapString(final String input) {
		assert input != null;
		return WordUtils.wrap(input, MAXLENGTH, null, false);
		
	}
	
	public ReportTextVisitor(final String filename) throws IOException {
		assert filename != null;
		this.writer = new PrintWriter(filename);
	}
	
	@Override
    public final void visitTitle(final Title title) {
		assert title != null;
		
		this.writer.println(title.getText());
		
	}
	
	@Override
    public final void visitLine(final Line line) {
		assert line != null;
		for (int i = 0; i < ReportTextVisitor.MAXLENGTH; i++) {
			this.writer.print("-");
		}
		this.writer.println();
	}
	
	@Override
    public final void visitFooter(final Footer footer) {
		assert footer != null;
		this.writer.println(footer.getText());
		
	}
	
	@Override
    public final void visitSubtitle(final Subtitle subtitle) {
		assert subtitle != null;
		this.writer.println(subtitle.getText());
		
	}
	
	@Override
    public final void visitParagraph(final Paragraph paragraph) {
		assert paragraph != null;
		String paragraphText = paragraph.getText();
		this.writer.println(wrapString(paragraphText));
		
	}
	
	
    @Override
    public final void visitTable(final Taula table) {
    	assert table != null;
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
