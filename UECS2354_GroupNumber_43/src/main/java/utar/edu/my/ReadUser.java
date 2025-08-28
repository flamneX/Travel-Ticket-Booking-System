package utar.edu.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadUser extends FileFunction {
	private final BufferedReader userReader;
	
	public ReadUser(BufferedReader userReader) {
		this.userReader = userReader;
	}
	
	// Read User Data from File
	public List<IUser> readFile() {
		List<IUser> users = new ArrayList<>();
		
		// Retrieve User Details from File
		try (userReader) {
			
			String userLine;
			while((userLine = userReader.readLine()) != null) {
				String[] userDetails = userLine.split(";");
				
				// Invalid Details
				if (userDetails.length != 4)
					throw new IllegalArgumentException("Invalid User Details");
				// Save Details in List
				else
					users.add(new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3]));
			}
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error Reading File");
		}
		
		return users;
	}
}