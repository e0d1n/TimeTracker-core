package Core;

import java.util.Observable;
import java.util.Observer;


public class Printer implements Observer {
	private Project root;
	
	public Printer(Project root) {
		// TODO Auto-generated constructor stub
		this.root = root;
	}

	
	public void printAll(){
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
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Nom           Temps inici                     Temps final            Durada (hh:mm:ss)");
		System.out.println("----+------------------------------+-------------------------------+------------------");
		printAll();
	}

}
