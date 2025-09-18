package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class GuestFileIntegrationTest {
	// Mock and Target Class
	private GuestFile gf;
	// Sample Guest Objects
	private Guest validGuest1 = new Guest("MIKU", "miku@gmail.com", "0123456789");
	private Guest validGuest2 = new Guest("TETO", "teto@gmail.com", "0123456789");
	private Guest validGuest3 = new Guest("GUMI", "gumi@gmail.com"	, "0121892398");
	private Guest validGuest4 = new Guest("LEN"	, "len@gmail.com"	, "0129850802");
	private Guest validGuest5 = new Guest("RIN"	, "rin@gmail.com"	, "0123435887");
	private Guest invalidGuest = new Guest("MIKU", "miku@gmail.com", "");
	// File Paths
	private String validFile = "TestData\\guestDummy.txt";
	
	
	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Mock and Class
		gf = new GuestFile();
	}


	// Read & Write Guest
	// Valid Parameters
	private Object getReadWriteIntegrationValidParams() {
		// Sample Array
		Guest[] array1 = {validGuest1, validGuest2};
		Guest[] array2 = {validGuest2, validGuest3};
		Guest[] array3 = {validGuest3, validGuest4};
		Guest[] array4 = {validGuest4, validGuest5};
		Guest[] array5 = {validGuest5, validGuest1};
		
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
	public void testReadWriteIntegrationValid(Guest[] readWriteData) {
		
		// Run Method
		gf.writeGuestToFile(readWriteData, validFile);
		List<Guest> guestList = gf.readGuestFromFile(validFile);
		Guest[] result = guestList.toArray(new Guest[guestList.size()]);
			
		// Compare Results
		assertArrayEquals(result, readWriteData);
	}


	// Read Guest From File
	// Invalid File Data Format
	@Test (expected = IllegalArgumentException.class)
	public void testReadGuestFromFileInvalid() {

		// Sample Data
		Guest[] readWriteData = {invalidGuest};
			
		// Run Method
		gf.writeGuestToFile(readWriteData, validFile);
		gf.readGuestFromFile(validFile);
	}
	
	// Invalid Parameters
	private Object getInvalidReadGuestFromFileParams() {
		return new Object[] {
			new Object[] {null},			// Null File Name
			new Object[] {""},				// Invalid File Path
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidReadGuestFromFileParams")
	public void testReadGuestFromFileInvalid2(String fileName) {
		
		// Run Method
		gf.readGuestFromFile(fileName);
	}


	// Write Guest To File
	// Invalid Parameters
	private Object getInvalidWriteGuestToFileParams() {
		Guest[] sampleArray = {validGuest1, validGuest2};
		
		return new Object[] {
			new Object[] {null, validFile},				// Null Guest List
			new Object[] {sampleArray, null},			// Null File Name
			new Object[] {sampleArray, ""},				// Invalid File Path
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidWriteGuestToFileParams")
	public void testWriteUserToFileInvalid(Guest[] guestArray, String fileName) {
		
		// Run Method
		gf.writeGuestToFile(guestArray, fileName);
	}
}