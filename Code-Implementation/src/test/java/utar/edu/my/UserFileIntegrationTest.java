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
	private User validUser1 = new User("USER001", "MIKU", "miku@gmail.com"	, "0123456789");
	private User validUser2 = new User("USER002", "TETO", "teto@gmail.com"	, "0198765432");
	private User validUser3 = new User("USER003", "GUMI", "gumi@gmail.com"	, "0121892398");
	private User validUser4 = new User("USER004", "LEN"	, "len@gmail.com"	, "0129850802");
	private User validUser5 = new User("USER005", "RIN"	, "rin@gmail.com"	, "0123435887");
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
	private Object getReadWriteIntegrationValidParams() {
		// Sample Array
		User[] array1 = {validUser1, validUser2};
		User[] array2 = {validUser2, validUser3};
		User[] array3 = {validUser3, validUser4};
		User[] array4 = {validUser4, validUser5};
		User[] array5 = {validUser5, validUser1};
		
		return new Object[] {
			new Object[] {array1},
			new Object[] {array2},
			new Object[] {array3},
			new Object[] {array4},
			new Object[] {array5}
		};
	}
	
	@Test
	@Parameters (method = "getReadWriteIntegrationValidParams")
	public void testReadWriteIntegrationValid(User[] readWriteData) {
		
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