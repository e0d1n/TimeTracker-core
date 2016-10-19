package Core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialSave{

	/** Declaration of the logger
	 *  used for debugging purposes
	 */
	static Logger logger = LoggerFactory.getLogger("SerialSave");
	
	/**
	 * Save: Saves a project with serialization to a file
	 * @project: the project to save
	 */
	public void Save(Project project) throws FileNotFoundException, IOException {
		logger.debug("Saving serialized file to timetask.ser");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("timetask.ser"));
		oos.writeObject(project);
		logger.debug("Serialized file saved");
	}

	/**
	 * Load: Load a project from a serialized file
	 */
	public Project Load() throws FileNotFoundException, IOException, ClassNotFoundException {
		logger.debug("Loading serialized file from timetask.ser");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("timetask.ser"));
		Project project = (Project) ois.readObject();
		logger.debug("Serialized file loaded");
		return project;
	}
	
}
