package Core;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Saver {

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
		 */
	public abstract void Save(Project project) throws FileNotFoundException, IOException;

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
			 */
	public abstract Project Load() throws FileNotFoundException, IOException, ClassNotFoundException;

}
