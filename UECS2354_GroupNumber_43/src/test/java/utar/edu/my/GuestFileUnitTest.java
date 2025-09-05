package utar.edu.my;

import static org.junit.Assert.*;
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
	@Test
	public void testReadGuestFromFileValid() {
			
		// Sample Data
		String[] fileReadOutput = {validGuest1.toString(), validGuest2.toString()};
		Guest[] expectedArray = {validGuest1, validGuest2};
			
		// Setup Mock Return Value
		when(ffMock.readFromFile(validFile)).thenReturn(fileReadOutput);
				
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
	public void testReadGuestFromFileInvalid(String fileRead) {
		
		// Sample Data
		String[] fileReadOutput = {"MIKU;miku@gmail.com"};
			
		// Setup Mock Return Value
		when(ffMock.readFromFile(validFile)).thenReturn(fileReadOutput);
				
		// Run Method
		gf.readGuestFromFile(validFile);
	}
			
	// File Reader Exception
	@Test (expected = IllegalArgumentException.class)
	public void testReadUserFromFileInvalid2() {
			
		// Setup Mock to Throw Error
		when(ffMock.readFromFile(validFile)).thenThrow(new IllegalArgumentException());
				
		// Run Method
		gf.readGuestFromFile(validFile);
	}
	
	
	// Write Guest To File
	// Valid Parameters
	@Test
	public void testWriteGuestToFileValid() {
			
		// Sample Data
		Guest[] writeArray = {validGuest1, validGuest2};
		String[] expectedArray = {validGuest1.toString(), validGuest2.toString()};
			
		// Run Method
		gf.writeGuestToFile(writeArray, validFile);
			
		verify(ffMock, times(1)).writeToFile(expectedArray, validFile);
	}
		
	// Invalid Parameters
	@Test (expected = IllegalArgumentException.class)
	public void testWriteGuestToFileInvalid() {
			
		// Run Method
		gf.writeGuestToFile(null, validFile);		// Null Guest Array
	}
		
	// File Writer Exception
	@Test(expected=IllegalArgumentException.class)
	public void testWriteGuestToFileInvalid2() {
		
		// Setup Throw Exception
		doThrow(new IllegalArgumentException())
			.when(ffMock).writeToFile(any(String[].class), anyString());
			
		// Run Method
		gf.writeGuestToFile(new Guest[0], validFile);
	}
}
