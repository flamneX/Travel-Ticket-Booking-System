package utar.edu.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadUser extends FileFunction {

	// Read User Data from File
	public List<IUser> readFile() throws IOException {
		List<IUser> users = new ArrayList<>();
		// File Path
		Path userPath = cDirectory.resolve(Paths.get("user.txt"));
		
		// Retrieve User Details from File
		BufferedReader userReader = Files.newBufferedReader(userPath);
		String userLine;
		while((userLine = userReader.readLine()) != null) {
				
			// Insert User Details
			String[] userDetails = userLine.split(";");
			users.add(new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3]));
		}
		userReader.close();
		
		return users;
	}
	
	// Get Specific User Data by ID
	public IUser getUser(String userID) throws IOException {
		List<IUser> userList = readFile();
		
		// Search User
		for (IUser user : userList) {
			if (((User)user).getID().toUpperCase().equals(userID.toUpperCase())) {
				return user;
			}
		}
		throw new IOException();
	}
}