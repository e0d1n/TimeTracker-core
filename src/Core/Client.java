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
		Clock clock = new Clock(1000);
		Project proot = new Project("root","",null);
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*int auxiliar = 0;
		// TODO Auto-generated method stub
		if(auxiliar ==1){
		Project project = new Project("Universitat","Tasques d universitat", null);
		Project projectnew = new Project("Disseny de software", "Practiques",project);
		project.addActivity(projectnew);
		Task task = new Task("Practica1", "Projecte",projectnew);
		//Task task2 = new Task("Practica2", "Projecte2",projectnew);
		projectnew.addActivity(task);
		task.start(clock);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		task.stop(clock);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("timetask.ser"));
			System.out.println("serialize");
			oos.writeObject(project);
			System.out.println("serialize");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else{
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("timetask.ser"));
				try {
					Project project = (Project) ois.readObject();
					System.out.println(project);
					Project projectnew = (Project) project.activities.get(0);
					Task task = (Task) projectnew.activities.get(0);
					task.start(clock);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					task.stop(clock);
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("timetask.ser"));
					System.out.println("serialize");
					oos.writeObject(project);
					System.out.println("serialize");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			
		}
		System.out.println("serialize");
		//task2.start(clock);
		
		/*
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//task2.stop(clock);

		//task.stop(clock);
	}
}
