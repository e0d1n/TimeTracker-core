package Core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
	
	public static void a1Test() throws InterruptedException, FileNotFoundException, IOException{
		Clock clock2 = new Clock(2000);
		Clock clock = new Clock(1000);
		Project proot = new Project("RT","",null);
		Printer printer = new Printer(proot);
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
		printer.printAll();
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
		
		/*
		SerialSave cargar = new SerialSave();
		Project project = cargar.Load();
		*/
	}
	public static void a2Test() throws InterruptedException{
		Clock clock = new Clock(1000);
		Clock clock2 = new Clock(2000);
		Project proot = new Project("RT","proot",null);
		Project project = new Project("P1","p1",proot);
		proot.addActivity(project);
		Task T3 = new Task("T3","",project);
		project.addActivity(T3);
		Project neew = new Project("P2","",project);
		project.addActivity(neew);
		Task T1 = new Task("T1","",neew);
		Task T2 = new Task("T2","",neew);
		neew.addActivity(T1);
		neew.addActivity(T2);
		Printer printer = new Printer(proot);
		clock2.addObserver(printer);
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
	
	/**
	 */
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {
			
			a2Test();	
	}

}
