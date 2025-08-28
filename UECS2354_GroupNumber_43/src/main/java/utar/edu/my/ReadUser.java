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
	public List<IUser> readFile(Path filePath) {
		List<IUser> users = new ArrayList<>();
		
		// Retrieve User Details from File
		try (BufferedReader userReader = Files.newBufferedReader(filePath)) {
			String userLine;
			while((userLine = userReader.readLine()) != null) {
				
				// Insert User Details
				String[] userDetails = userLine.split(";");
				users.add(new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3]));
			}
		}
		catch (IOException e) {
			System.out.print("Hi");
		}
		
		return users;
	}
	
	// Get Specific User Data by ID
	public IUser getUser(String userID) throws IOException {
		List<IUser> userList = readFile(Paths.get("student"));
		
		// Search User
		for (IUser user : userList) {
			if (((User)user).getID().toUpperCase().equals(userID.toUpperCase())) {
				return user;
			}
		}
		throw new IOException();
	}
}