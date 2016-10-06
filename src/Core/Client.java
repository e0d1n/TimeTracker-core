package Core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Clock clock = new Clock(2000);
		Project proot = new Project("root","",null);
		Project project = new Project("P1","",proot);
		proot.addActivity(project);
		Task T3 = new Task("T3","",project);
		project.addActivity(T3);
		Project neew = new Project("P2","",project);
		project.addActivity( neew);
		Task T1 = new Task("T1","",project);
		Task T2 = new Task("T2","",project);
		neew.addActivity(T1);
		neew.addActivity(T2);
		
	}
}
