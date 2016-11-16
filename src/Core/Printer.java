package core;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Printer implements Observer {

    static Logger logger = LoggerFactory.getLogger("Printer");

    //Used to store our root project node.
    private Activity root;

    /**
     * Constructor Printer
     * Visitable class of the visitor pattern for print activities
     * @param root: Project root
     */
    public Printer(Activity root) {
        this.root = root;
    }

    /**
     * Used to call root node accept, so that it can recursively call
     * all sub activities accept, and print our nodes as Visitable print's
     * describe for each of the called object.
     */
    private void printAll() {
        this.root.acceptPrinter(this);
    }

    
    /**
     * Print function of visitor pattern that accepts
     * an Activity as parameter.
     * @param activity <Activity>
     */
    public void print(Activity activity) {
        logger.debug("Printing Activity "+ activity.name);
        System.out.println(activity);
    }
    
    /**
     * update: Method of the observer 
     * Prints the header and the other elements of the project
     */
    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Nom        Temps inici             Temps final        Durada (hh:mm:ss)");
        System.out.println("----+-----------------------+-----------------------+------------------");
        printAll();
    }

}
