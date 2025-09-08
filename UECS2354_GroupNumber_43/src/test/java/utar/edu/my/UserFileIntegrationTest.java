package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserFileIntegrationTest {
	// Mock and Target Class
	private UserFile uf;
	// Sample User Objects
	private User validUser1 = new User("USER001", "MIKU", "miku@gmail.com", "0123456789");
	private User validUser2 = new User("USER002", "TETO", "teto@gmail.com", "0123456789");
	private User invalidUser = new User("USER001", "MIKU", "miku@gmail.com", "");
	// File Paths
	private String validFile = "TestData\\user.txt";
	
	
	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Mock and Class
		uf = new UserFile();
	}
	
	
	// Read & Write User
	// Valid Parameters
	@Test
	public void testReadWriteIntegrationValid() {
		
		// Sample Data
		User[] readWriteData = {validUser1, validUser2};
		
		// Run Method
		uf.writeUserToFile(readWriteData, validFile);
		List<User> userList = uf.readUserFromFile(validFile);
		User[] result = userList.toArray(new User[userList.size()]);
			
		// Compare Results
		assertArrayEquals(result, readWriteData);
	}
	
	
	// Read User From File
	// Invalid File Data Format
	@Test (expected = IllegalArgumentException.class)
	public void testReadUserFromFileInvalid() {

		// Sample Data
		User[] readWriteData = {invalidUser};
			
		// Run Method
		uf.writeUserToFile(readWriteData, validFile);
		uf.readUserFromFile(validFile);
	}
	
	// Invalid Parameters
	private Object getInvalidReadUserFromFileParams() {
		return new Object[] {
			new Object[] {null},			// Null File Name
			new Object[] {""}				// Invalid File Path
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidReadUserFromFileParams")
	public void testReadUserFromFileInvalid2(String fileName) {
		
		// Run Method
		uf.readUserFromFile(fileName);
	}


	// Write User To File
	// Invalid Parameters
	private Object getInvalidWriteUserToFileParams() {
		User[] sampleArray = {validUser1, validUser2};
		
		return new Object[] {
			new Object[] {null, validFile},				// Null User List
			new Object[] {sampleArray, null},			// Null File Name
			new Object[] {sampleArray, ""}				// Invalid File Path
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidWriteUserToFileParams")
	public void testWriteUserToFileInvalid(User[] userArray, String fileName) {
		
		// Run Method
		uf.writeUserToFile(userArray, fileName);
	}
}