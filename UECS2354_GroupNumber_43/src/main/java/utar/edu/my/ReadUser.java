package utar.edu.my;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadUser extends FileFunction {

	// Read User Data from File
	public List<IUser> readFile() {
		List<IUser> users = new ArrayList<>();
		// File Path
		Path userPath = cDirectory.resolve(Paths.get("user.txt"));

		// Read if File Exists
		if (Files.exists(userPath)) {
			try (BufferedReader userReader = Files.newBufferedReader(userPath)) {
				
				// Retrieve User Details
				String userLine;
				while((userLine = userReader.readLine()) != null) {
					
					// Insert User Details
					String[] userDetails = userLine.split(";");
					users.add(new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3]));
				}
			}
			catch (IOException e) {
				System.out.println("Error Reading File: " + e);
			}
		}
		
		// Create Directory & File if File does not Exists
		else {
			try {
				new File(userPath.normalize().toString()).createNewFile();
			}
			catch (IOException e) {
				System.out.println("Error Creating File: " + e);
			}
		}
		return users;
	}
	
	// Get Specific User Data by ID
	public IUser getUser(String userID) {
		List<IUser> userList = readFile();
		
		// Search User
		for (IUser user : userList) {
			if (((User)user).getID().equals(userID)) {
				return user;
			}
		}
		return null;
	}
}