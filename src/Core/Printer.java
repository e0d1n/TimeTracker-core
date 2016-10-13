package Core;

import java.util.Observable;
import java.util.Observer;


public class Printer implements Observer {
	private Project root;
	
	/**
	 * Constructor Printer
	 * @param root: Project root
	 */
	public Printer(Project root) {
		// TODO Auto-generated constructor stub
		this.root = root;
	}

	private void printAll(){
		this.root.accept(this);
	}
	
	public void print(Interval interval) {
		// TODO Auto-generated method stub
		System.out.println(interval);
	}

	public void print(Task task) {
		// TODO Auto-generated method stub
		System.out.println(task);
	}

	public void print(Project project) {
		// TODO Auto-generated method stub
		System.out.println(project);		
	}
	
	/**
	 * update: Method of the observer 
	 * Prints the header and the other elements of the project
	 * Sleep 250ms because we need sincronize the update of the activities with 
	 * the print of the tree
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Nom           Temps inici                     Temps final            Durada (hh:mm:ss)");
		System.out.println("----+------------------------------+-------------------------------+------------------");
		printAll();
	}

}
