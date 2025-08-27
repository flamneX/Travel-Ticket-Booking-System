package utar.edu.my;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddNewUser extends FileFunction {
	// Write New User to File
	public void WriteFile(IUser newUser) {
		// File Path
		Path userPath = cDirectory.resolve(Paths.get("user.txt"));
		
		// Write Data to File
		try (BufferedWriter userWriter = Files.newBufferedWriter(userPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			userWriter.write(newUser.toString());
			userWriter.newLine();
		}
		catch (IOException e) {
			System.out.println("Error Writing to File: " + e.getMessage());
		}
	}
}