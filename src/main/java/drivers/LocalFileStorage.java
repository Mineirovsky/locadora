package drivers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import contracts.drivers.IStorage;

public class LocalFileStorage implements IStorage {
	private static final String BASE_DIR = "data";

	@Override
	public File file(String fileName) {
		return new File(BASE_DIR + "/" + fileName);
	}

	@Override
	public boolean saveContents(String fileName, String contents) throws IOException {
		File file = file(fileName);
		boolean isNewFile = file.createNewFile();
		FileWriter writer = getWriter(file);

		writer.write(contents);
		writer.close();

		return isNewFile;
	}

	@Override
	public boolean append(String fileName, String contents) throws IOException {
		File file = file(fileName);
		FileWriter writer;

		if (!file.exists()) {
			return false;
		}

		writer = getWriter(file);
		writer.append(contents);
		writer.close();

		return true;
	}

	private FileWriter getWriter(File file) throws IOException {
		return new FileWriter(file);
	}
}
