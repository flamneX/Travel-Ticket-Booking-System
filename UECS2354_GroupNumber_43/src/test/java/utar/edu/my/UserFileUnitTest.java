package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserFileUnitTest {
	// Mock and Target Class
	private FileFunctionality ffMock;
	private UserFile uf;
	// Sample User Objects
	private User validUser1 = new User("USER001", "MIKU", "miku@gmail.com"	, "0123456789");
	private User validUser2 = new User("USER002", "TETO", "teto@gmail.com"	, "0198765432");
	private User validUser3 = new User("USER003", "GUMI", "gumi@gmail.com"	, "0121892398");
	private User validUser4 = new User("USER004", "LEN"	, "len@gmail.com"	, "0129850802");
	private User validUser5 = new User("USER005", "RIN"	, "rin@gmail.com"	, "0123435887");
	// File Paths
	private String validFile = "TestData\\userDummy.txt";
	
	
	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Mock and Class
		ffMock = mock(FileFunctionality.class);
		uf = new UserFile(ffMock);
	}
	
	
	// Read User From File
	// Valid Output
	private Object getReadUserFromFileValidParams() {
		// Sample Arrays
		String[] result1 = {validUser1.toString(), validUser2.toString()};
		String[] result2 = {validUser2.toString(), validUser3.toString()};
		String[] result3 = {validUser3.toString(), validUser4.toString()};
		String[] result4 = {validUser4.toString(), validUser5.toString()};
		String[] result5 = {validUser5.toString(), validUser1.toString()};
		
		User[] array1 = {validUser1, validUser2};
		User[] array2 = {validUser2, validUser3};
		User[] array3 = {validUser3, validUser4};
		User[] array4 = {validUser4, validUser5};
		User[] array5 = {validUser5, validUser1};
		
		return new Object[] {
			new Object[] {result1, array1},
			new Object[] {result2, array2},
			new Object[] {result3, array3},
			new Object[] {result4, array4},
			new Object[] {result5, array5}
		};
	}
	
	@Test
	@Parameters (method = "getReadUserFromFileValidParams")
	public void testReadUserFromFileValid(String[] readOutput, User[] expectedArray) {
		
		// Setup Mock Return Value
		when(ffMock.readFromFile(validFile)).thenReturn(readOutput);
			
		// Run Method
		List<User> userList = uf.readUserFromFile(validFile);
		User[] userArray = userList.toArray(new User[userList.size()]);
			
		// Compare Results
		assertArrayEquals(userArray, expectedArray);
	}
		
	// Invalid Output
	private Object[] getInvalidReadUserFromFileOutput() {
		return new Object[] {
			// Invalid User Detail Format
			new Object[] {"MIKU;miku@gmail.com;0123456789"},				// Less Data
			new Object[] {"USER001;MIKU;miku@gmail.com;0123456789;INVALID"}	// Additional Data
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidReadUserFromFileOutput")
	public void testReadUserFromFileInvalid(String fileReadLine) {

		// Sample Data
		String[] fileReadOutput = {fileReadLine};
		
		// Setup Mock Return Value
		when(ffMock.readFromFile(validFile)).thenReturn(fileReadOutput);
			
		// Run Method
		uf.readUserFromFile(validFile);
	}

	
	// Write User To File
	// Valid Parameters
	private Object getWriteUserToFileValidParams() {
		// Sample Arrays
		User[] array1 = {validUser1, validUser2};
		User[] array2 = {validUser2, validUser3};
		User[] array3 = {validUser3, validUser4};
		User[] array4 = {validUser4, validUser5};
		User[] array5 = {validUser5, validUser1};
		
		String[] result1 = {validUser1.toString(), validUser2.toString()};
		String[] result2 = {validUser2.toString(), validUser3.toString()};
		String[] result3 = {validUser3.toString(), validUser4.toString()};
		String[] result4 = {validUser4.toString(), validUser5.toString()};
		String[] result5 = {validUser5.toString(), validUser1.toString()};
		
		return new Object[] {
			new Object[] {array1, result1},
			new Object[] {array2, result2},
			new Object[] {array3, result3},
			new Object[] {array4, result4},
			new Object[] {array5, result5}
		};
	}
	
	@Test
	@Parameters (method = "getWriteUserToFileValidParams")
	public void testWriteUserToFileValid(User[] writeArray, String[] expectedArray) {
		
		// Run Method
		uf.writeUserToFile(writeArray, validFile);
		
		// Verify Method Execution
		verify(ffMock, times(1)).writeToFile(expectedArray, validFile);
	}

	// Invalid Parameters
	@Test (expected = IllegalArgumentException.class)
	public void testWriteUserToFileInvalid() {
		
		// Run Method
		uf.writeUserToFile(null, validFile);	// Null User List
	}
}