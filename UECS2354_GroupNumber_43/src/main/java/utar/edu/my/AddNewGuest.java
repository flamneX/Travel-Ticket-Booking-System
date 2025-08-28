package utar.edu.my;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddNewGuest extends FileFunction {
	private final Path guestPath;
	
	public AddNewGuest(Path guestPath) {
		this.guestPath = guestPath;
	}
	
	// Write New User to File
	public void WriteFile(IUser newGuest) throws IOException {
		
		if (newGuest == null) {
			throw new IllegalArgumentException("Null Guest");
		}
		
		
		// Write Data to File
		try (BufferedWriter guestWriter = Files.newBufferedWriter(guestPath, StandardOpenOption.APPEND)) {
			guestWriter.write(newGuest.toString());
			guestWriter.newLine();
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error Writing To File");
		}
	}
}