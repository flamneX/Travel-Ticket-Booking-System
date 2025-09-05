package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
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
	private User validUser1 = new User("USER001", "MIKU", "miku@gmail.com", "0123456789");
	private User validUser2 = new User("USER002", "TETO", "teto@gmail.com", "0123456789");
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
	@Test
	public void testReadUserFromFileValid() {
		
		// Sample Data
		String[] fileReadOutput = {validUser1.toString(), validUser2.toString()};
		User[] expectedArray = {validUser1, validUser2};
		
		// Setup Mock Return Value
		when(ffMock.readFromFile(validFile)).thenReturn(fileReadOutput);
			
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
		
	// File Reader Exception
	@Test (expected = IllegalArgumentException.class)
	public void testReadUserFromFileInvalid2() {
		
		// Setup Mock to Throw Error
		when(ffMock.readFromFile(validFile)).thenThrow(new IllegalArgumentException());
			
		// Run Method
		uf.readUserFromFile(validFile);
	}

	
	// Write User To File
	// Valid Parameters
	@Test
	public void testWriteUserToFileValid() {
		
		// Sample Data
		User[] writeArray = {validUser1, validUser2};
		String[] expectedArray = {validUser1.toString(), validUser2.toString()};
		
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
	
	// File Writer Exception
	@Test(expected=IllegalArgumentException.class)
	public void testWriteUserToFileInvalid2() {
		
		// Setup Throw Exception
		doThrow(new IllegalArgumentException())
			.when(ffMock).writeToFile(any(String[].class), anyString());
		
		// Run Method
		uf.writeUserToFile(new User[0], validFile);
	}
}