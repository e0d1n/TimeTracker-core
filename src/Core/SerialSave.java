package Core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialSave{

	/**
	 * Save: Saves a project with serialization to a file
	 * @project: the project to save
	 */
	public void Save(Project project) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("timetask.ser"));
		oos.writeObject(project);
	}

	/**
	 * Load: Load a project from a serialized file
	 */
	public Project Load() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("timetask.ser"));
		Project project = (Project) ois.readObject();
		return project;
	}
	
}
