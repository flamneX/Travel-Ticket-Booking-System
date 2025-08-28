package utar.edu.my;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddNewUser extends FileFunction {
	private final BufferedWriter userWriter;
	
	public AddNewUser(BufferedWriter userWriter) {
		this.userWriter = userWriter;
	}
	
	// Write New User to File
	public void WriteFile(IUser newUser) {
		
		if (newUser == null) {
			throw new IllegalArgumentException("Null User");
		}
		
		try (userWriter) {
			userWriter.write(newUser.toString());
			userWriter.newLine();
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error Writing To File");
		}
	}
}