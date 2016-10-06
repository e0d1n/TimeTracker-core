package Core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerialSave implements Saver {

	@Override
	public void Save(Project project) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("timetask.ser"));
		System.out.println("serialize");
		oos.writeObject(project);
	}

	@Override
	public Project Load() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("timetask.ser"));
		Project project = (Project) ois.readObject();
		return project;
	}

}
