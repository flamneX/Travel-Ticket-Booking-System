package utar.edu.my;

import java.util.ArrayList;
import java.util.List;

public class UserFile {
	private FileFunctionality ff;
	
	// Constructors
	public UserFile() {
		ff = new FileFunctionality();
	}
	
	public UserFile(FileFunctionality ff) {
		this.ff = ff;
	}
	
	
	// Read User Data from File
	public List<User> readUserFromFile(String fileName) {
		List<User> userList = new ArrayList<> ();
			
		// Retrieve User Details from File
		String[] userArray = ff.readFromFile(fileName);
			
		// Construct User ArrayList
		for (String userLine : userArray) {
			String[] userDetail = userLine.split(";");
				
			// Invalid User File Format
			if (userDetail.length != 4)
				throw new IllegalArgumentException("Invalid User Detail Format");
			// Convert String to User Object
			else
				userList.add(new User(userDetail[0], userDetail[1], userDetail[2], userDetail[3]));
		}
					
		return userList;
	}
		
	// Write New User to File
	public void writeUserToFile(User[] userArray, String fileName) {
		List<String> userStringList = new ArrayList<>();
		
		// Null User Array
		if (userArray == null)
			throw new IllegalArgumentException("User List Cannot Be Null");
	
		// Convert User Class Array to String List
		for (User user : userArray) {
			String userString = user.toString();
			userStringList.add(userString);
		}
		
		// Convert Array List to Array
		String[] userStringArray = userStringList.toArray(new String[userStringList.size()]);
		
		// Write to File
		ff.writeToFile(userStringArray, fileName);
	}

}
