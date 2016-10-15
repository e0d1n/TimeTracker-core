package Core;

import java.util.Observable;
import java.util.Observer;


public class Printer implements Observer {

	/**
	 * Used to store our root project node.
	 */
	private Project root;

	/**
	 * Constructor Printer
	 * Visitable class of the visitor pattern for print activities
	 * @param root: Project root
	 */
	public Printer(Project root) {

		this.root = root;
	}

	/**
	 * Used to call root node accept, so that it can recursively call
	 * all sub activities accept, and print our nodes as Visitable print's
	 * describe for each of the called object.
	 */
	private void printAll(){
		
		this.root.accept(this);
	}

	/**
	 * Print function of visitor pattern that accepts
	 * an interval as parameter.
	 * @param interval <Interval>
	 */
	public void print(Interval interval) {
		
		System.out.println(interval);
	}

	/**
	 * Print function of visitor pattern that accepts
	 * a task as parameter. 
	 * @param task <Task> 
	 */
	public void print(Task task) {
		
		System.out.println(task);
	}

	/**
	 * Print function of visitor pattern that accepts
	 * a project as parameter.
	 * @param project <Project>
	 */
	public void print(Project project) {

		System.out.println(project);		
	}

	/**
	 * update: Method of the observer 
	 * Prints the header and the other elements of the project
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		/*
		# Sleep 250ms because we need sincronize the update of the activities with 
		# the print of the tree.
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}*/
		
		System.out.println("Nom           Temps inici                     Temps final            Durada (hh:mm:ss)");
		System.out.println("----+------------------------------+-------------------------------+------------------");
		printAll();
	}

}
