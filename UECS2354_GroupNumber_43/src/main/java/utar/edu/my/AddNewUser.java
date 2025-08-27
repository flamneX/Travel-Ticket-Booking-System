package utar.edu.my;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddNewUser extends FileFunction {
	// Write New User to File
	public void WriteFile(IUser newUser) throws IOException {
		// File Path
		Path userPath = cDirectory.resolve(Paths.get("user.txt"));
		
		// Write Data to File
		BufferedWriter userWriter = Files.newBufferedWriter(userPath, StandardOpenOption.APPEND);
		userWriter.write(newUser.toString());
		userWriter.newLine();
		userWriter.close();
	}
}