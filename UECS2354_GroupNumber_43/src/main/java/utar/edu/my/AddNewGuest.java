package utar.edu.my;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddNewGuest extends FileFunction {
	// Write New User to File
	public void WriteFile(IUser newGuest) throws IOException {
		// File Path
		Path guestPath = cDirectory.resolve(Paths.get("guest.txt"));
		
		// Write Data to File
		BufferedWriter guestWriter = Files.newBufferedWriter(guestPath, StandardOpenOption.APPEND);
		guestWriter.write(newGuest.toString());
		guestWriter.newLine();
		guestWriter.close();
	}
}