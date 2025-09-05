package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyString;

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
	private Guest invalidGuest = new Guest("MIKU", "miku@gmail.com", "");
	// File Paths
	private String validFile = "TestData\\guestDummy.txt";
	private String lockedFile = "TestData\\LockedFile.txt";
	
	
	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Mock and Class
		gf = new GuestFile();
	}
	
	
	// Read & Write Guest
	// Valid Parameters
	@Test
	public void testReadWriteIntegrationValid() {
		
		// Sample Data
		Guest[] readWriteData = {validGuest1, validGuest2};
		
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
			new Object[] {anyString()},		// Invalid File Name
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
			new Object[] {sampleArray, anyString()},	// Invalid File Name
			new Object[] {sampleArray, lockedFile}		// Locked File
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidWriteGuestToFileParams")
	public void testWriteUserToFileInvalid(Guest[] guestArray, String fileName) {
		
		// Run Method
		gf.writeGuestToFile(guestArray, fileName);
	}
}