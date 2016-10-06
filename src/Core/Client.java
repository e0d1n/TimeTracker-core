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
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {
		/*
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
		T3.start(clock);
		Thread.sleep(3000);
		T3.stop(clock);
		Thread.sleep(7000);
		T2.start(clock);
		Thread.sleep(10000);
		T2.stop(clock);
		T3.start(clock);
		Thread.sleep(2000);
		T3.stop(clock);
		SerialSave guardar = new SerialSave();
		guardar.Save(proot);
		*/
		/*
		SerialSave cargar = new SerialSave();
		Project project = cargar.Load();
		System.out.println(project);
		*/
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
		T3.start(clock);
		Thread.sleep(4000);
		T2.start(clock);
		Thread.sleep(2000);
		T3.stop(clock);
		Thread.sleep(2000);
		T1.start(clock);
		Thread.sleep(4000);
		T1.stop(clock);
		Thread.sleep(2000);
		T2.stop(clock);
		Thread.sleep(4000);
		T3.start(clock);
		Thread.sleep(2000);
		T3.stop(clock);
		
	}
}
