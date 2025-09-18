package utar.edu.my;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GuestFileUnitTest {
	// Mock and Target Class
	private FileFunctionality ffMock;
	private GuestFile gf;
	// Sample Guest Objects
	private Guest validGuest1 = new Guest("MIKU", "miku@gmail.com", "0123456789");
	private Guest validGuest2 = new Guest("TETO", "teto@gmail.com", "0123456789");
	private Guest validGuest3 = new Guest("GUMI", "gumi@gmail.com"	, "0121892398");
	private Guest validGuest4 = new Guest("LEN"	, "len@gmail.com"	, "0129850802");
	private Guest validGuest5 = new Guest("RIN"	, "rin@gmail.com"	, "0123435887");
	// File Paths
	private String validFile = "TestData\\guestDummy.txt";
		
		
	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Mock and Class
		ffMock = mock(FileFunctionality.class);
		gf = new GuestFile(ffMock);
	}
	
	
	// Read Guest From File
	// Valid Output
	private Object getReadGuestFromFileValidParams() {
		// Sample Arrays
		String[] result1 = {validGuest1.toString(), validGuest2.toString()};
		String[] result2 = {validGuest2.toString(), validGuest3.toString()};
		String[] result3 = {validGuest3.toString(), validGuest4.toString()};
		String[] result4 = {validGuest4.toString(), validGuest5.toString()};
		String[] result5 = {validGuest5.toString(), validGuest1.toString()};
		
		Guest[] array1 = {validGuest1, validGuest2};
		Guest[] array2 = {validGuest2, validGuest3};
		Guest[] array3 = {validGuest3, validGuest4};
		Guest[] array4 = {validGuest4, validGuest5};
		Guest[] array5 = {validGuest5, validGuest1};
		
		return new Object[] {
			new Object[] {result1, array1},
			new Object[] {result2, array2},
			new Object[] {result3, array3},
			new Object[] {result4, array4},
			new Object[] {result5, array5}
		};
	}
		
	@Test
	@Parameters (method = "getReadGuestFromFileValidParams")
	public void testReadGuestFromFileValid(String[] readOutput, Guest[] expectedArray) {
			
		// Setup Mock Return Value
		when(ffMock.readFromFile(validFile)).thenReturn(readOutput);
				
		// Run Method
		List<Guest> guestList = gf.readGuestFromFile(validFile);
		Guest[] guestArray = guestList.toArray(new Guest[guestList.size()]);
				
		// Compare Results
		assertArrayEquals(guestArray, expectedArray);
	}
			
	// Invalid Output
	private Object[] getInvalidReadGuestFromFileOutput() {
		return new Object[] {
			// Invalid Guest Detail Format
			new Object[] {"MIKU;miku@gmail.com"},					// Less Data
			new Object[] {"MIKU;miku@gmail.com;0123456789;INVALID"}	// Additional Data
		};
	}
		
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidReadGuestFromFileOutput")
	public void testReadGuestFromFileInvalid(String fileReadLine) {
		
		// Sample Data
		String[] fileReadOutput = {fileReadLine};
			
		// Setup Mock Return Value
		when(ffMock.readFromFile(validFile)).thenReturn(fileReadOutput);
				
		// Run Method
		gf.readGuestFromFile(validFile);
	}
	
	
	// Write Guest To File
	// Valid Parameters
	private Object getWriteGuestToFileValidParams() {
		// Sample Arrays
		Guest[] array1 = {validGuest1, validGuest2};
		Guest[] array2 = {validGuest2, validGuest3};
		Guest[] array3 = {validGuest3, validGuest4};
		Guest[] array4 = {validGuest4, validGuest5};
		Guest[] array5 = {validGuest5, validGuest1};

		String[] result1 = {validGuest1.toString(), validGuest2.toString()};
		String[] result2 = {validGuest2.toString(), validGuest3.toString()};
		String[] result3 = {validGuest3.toString(), validGuest4.toString()};
		String[] result4 = {validGuest4.toString(), validGuest5.toString()};
		String[] result5 = {validGuest5.toString(), validGuest1.toString()};
		
		return new Object[] {
			new Object[] {array1, result1},
			new Object[] {array2, result2},
			new Object[] {array3, result3},
			new Object[] {array4, result4},
			new Object[] {array5, result5}
		};
	}
	
	@Test
	@Parameters (method = "getWriteGuestToFileValidParams")
	public void testWriteGuestToFileValid(Guest[] writeArray, String[] expectedArray) {
			
		// Run Method
		gf.writeGuestToFile(writeArray, validFile);
		
		// Verify Method Execution
		verify(ffMock, times(1)).writeToFile(expectedArray, validFile);
	}
		
	// Invalid Parameters
	@Test (expected = IllegalArgumentException.class)
	public void testWriteGuestToFileInvalid() {
			
		// Run Method
		gf.writeGuestToFile(null, validFile);		// Null Guest Array
	}
}
