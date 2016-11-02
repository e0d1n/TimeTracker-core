package reports;

import java.io.*;
import org.apache.commons.lang3.text.*;


public class ReportTextVisitor extends ReportVisitor  {

   private PrintWriter writer;
   private int maxLength = 102;
   
   private String wrapString(String input) {
       return WordUtils.wrap(input, maxLength, null, false);
       
   }
    
   public ReportTextVisitor(PrintWriter writer2) throws IOException{
       this.writer = writer2;
   }
    
    @Override
    public void visitTitle(Title title) {
        
        this.writer.println(title.getText());
        
    }

    @Override
    public void visitLine(Line line) {

        
        for(int i = 0; i < this.maxLength; i++){
            this.writer.print("-");
        }
        this.writer.println();
    }

    @Override
    public void visitFooter(Footer footer) {
        
        this.writer.println(footer.getText());
        
    }

    @Override
    public void visitSubtitle(Subtitle subtitle) {
        
        this.writer.println(subtitle.getText());
        
    }

    @Override
    public void visitParagraph(Paragraph paragraph) {
        String paragraphText = paragraph.getText();
        this.writer.println(wrapString(paragraphText));

    }

    @Override
    public void visitTable(Taula table) {
        this.writer.println(table.getTaula());
        
    }
}
