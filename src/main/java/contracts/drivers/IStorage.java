package contracts.drivers;

import java.io.File;
import java.io.IOException;

public interface IStorage {
	/**
	 * Open a file from the storage
	 * @param fileName
	 * @return The file instance or null if not found
	 */
	public File file(String fileName);
	
	/**
	 * Save the contents to a file
	 * 
	 * IMPORTANT: overwrites whole document
	 * @param fileName
	 * @param contents
	 * @return True if a new file was created
	 */
	public boolean saveContents(String fileName, String contents) throws IOException;
	
	/**
	 * Append content to a file
	 * @param fileName
	 * @param contents
	 * @return True if file was found
	 */
	public boolean append(String fileName, String contents) throws IOException;
}
