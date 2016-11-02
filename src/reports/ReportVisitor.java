package reports;


public abstract class ReportVisitor {

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
    
}
