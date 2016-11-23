package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialSave {
	
	/**
	 * Declaration of the logger used for debugging purposes
	 */
	private static Logger logger = LoggerFactory.getLogger("SerialSave");
	
	/**
	 * Save: Saves a project with serialization to a file
	 * 
	 * @project: the project to save
	 */
	public final void Save(final Activity activity)
	        throws FileNotFoundException, IOException {
		assert activity != null;
		logger.debug("Saving serialized file to timetask.ser");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
		        "timetask.ser"));
		oos.writeObject(activity);
		logger.debug("Serialized file saved");
	}
	
	/**
	 * Load: Load a project from a serialized file
	 */
	public final Activity Load() throws FileNotFoundException, IOException,
	        ClassNotFoundException {
		logger.debug("Loading serialized file from timetask.ser");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
		        "timetask.ser"));
		Activity activity = (Activity) ois.readObject();
		logger.debug("Serialized file loaded");
		return activity;
	}
	
}
