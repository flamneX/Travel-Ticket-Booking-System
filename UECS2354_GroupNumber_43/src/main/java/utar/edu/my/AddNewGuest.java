package utar.edu.my;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddNewGuest extends FileFunction {
	// Write New User to File
	public void WriteFile(IUser newGuest) {
		// File Path
		Path guestPath = cDirectory.resolve(Paths.get("guest.txt"));
		
		// Write Data to File
		try (BufferedWriter guestWriter = Files.newBufferedWriter(guestPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			guestWriter.write(newGuest.toString());
			guestWriter.newLine();
		}
		catch (IOException e) {
			System.out.println("Error Writing to File: " + e.getMessage());
		}
	}
}