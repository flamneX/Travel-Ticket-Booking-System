package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

@RunWith (JUnitParamsRunner.class)
public class FileFunctionalityUnitTest {
	// Target Class
	private FileFunctionality ff;
	// Sample File Read/Write
	private String[] sampleArray = {"USER001;MIKU;miku@gmail.com;0123456789", "USER002;TETO;teto@gmail.com;0123456789"};
	// File Paths
	private String validFile = "TestData\\dummyFile1.txt";


	// Setup For all Test Classes
	@Before
	public void setupClasses() {
		ff = new FileFunctionality();
	}


	// Test for Both Read and Write Functionality
	@Test
	public void testReadnWriteFunction() {

		// Run Read n Write Method
		ff.writeToFile(sampleArray, validFile);
		String[] resultArray = ff.readFromFile(validFile);

		// Assert Results
		assertArrayEquals(resultArray, sampleArray);
	}


	// Write To File
	// Invalid Parameters
	private Object getWriteToFileInvalidParams() {
		return new Object[] {
			new Object[] {null, validFile},				// Null Input Array
			new Object[] {sampleArray, null},			// Null File Name
			new Object[] {sampleArray, anyString()},	// Invalid File Path
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getWriteToFileInvalidParams")
	public void testWriteToFileInvalid(String[] inputArray, String fileName) {
		
		// Run Method
		ff.writeToFile(inputArray, fileName);
	}


	// Read From File
	// Invalid Parameters
	private Object getReadFromFileInvalidParams() {
		return new Object[] {
			new Object[] {null},		// Null File Name
			new Object[] {anyString()}	// Invalid File Name
		};
	}

	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getReadFromFileInvalidParams")
	public void testReadFromFileInvalid(String fileName) {
		
		// Run Method
		ff.readFromFile(fileName);
	}
}