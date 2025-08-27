package utar.edu.my;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileFunction {
	// Get Database Directory
	Path cDirectory = getDirectory();

	public Path getDirectory() {
		Path absPath = Paths.get("").toAbsolutePath();
		Path directory;
		if (absPath.getFileName().toString().equals("UECS2354_GroupNumber_43")) {
			directory = absPath.resolve(absPath, Paths.get("Database"));
		}
		else {
			directory = absPath.resolve(absPath, Paths.get("UECS2354_GroupNumber_43", "Database"));
		}
		
		// Create Directory If it Does Not Exists
		if (!Files.exists(directory)) {
			new File(directory.normalize().toString()).mkdirs();
		}
		return directory;
	}

}
