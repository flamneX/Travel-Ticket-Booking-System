package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(JUnitParamsRunner.class)
public class ReadUserTest {

	// Read File
	// Valid Values
	private Object getValidReadFileParams() {
		return new Object[] {
			// User 1 + 2
			new Object[] {"USER001;RUI;rui@gmail.com;0123456789", "USER002;AKITO;akito@gmail.com;0123456789", 
					new ArrayList<IUser>(Arrays.asList(new User("USER001", "RUI", "rui@gmail.com", "0123456789"), 
							new User("USER002", "AKITO", "akito@gmail.com", "0123456789")))},
			// User 3 + 4
			new Object[] {"USER003;MAI;mai@gmail.com;0123456789", "USER004;NANAHO;nanaho@gmail.com;0123456789",
					new ArrayList<IUser>(Arrays.asList(new User("USER003", "MAI", "mai@gmail.com", "0123456789"), 
							new User("USER004", "NANAHO", "nanaho@gmail.com", "0123456789")))}
		};
	}
	
	@Test
	@Parameters (method = "getValidReadFileParams")
	public void testReadFileValidValues(String readLine1, String readLine2, List<IUser> expectedList) throws IOException {
		// Mock Read Buffer
		BufferedReader brMock = mock(BufferedReader.class);
		
		// Setup Return Values for Read Buffer
		when(brMock.readLine()).thenReturn(readLine1).thenReturn(readLine2).thenReturn(null);

		// Run Method
		ReadUser ur = new ReadUser(brMock);
		List<IUser> resultList = ur.readFile();
		
		verify(brMock, times(3)).readLine();
		assertArrayEquals(resultList.toArray(), expectedList.toArray());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testReadFileIOException() throws IOException {
		BufferedReader brMock = mock(BufferedReader.class);
		
		when(brMock.readLine()).thenThrow(new IOException());
		
		ReadUser ur = new ReadUser(brMock);
		ur.readFile();
	}
	
	// Invalid Values
	private Object getInvalidReadFileParams() {
		return new Object[] {
			// Invalid Array Size
			new Object[] {"USER001;RUI;rui@gmail.com;"},					// Detail Array Too Short
			new Object[] {"USER003;MAI;mai@gmail.com;0123456789;STUDENT"}	// Detail Array Too Long
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidReadFileParams")
	public void testReadFileInvalidValues(String readLine) throws IOException {
		BufferedReader brMock = mock(BufferedReader.class);
		
		when(brMock.readLine()).thenReturn(readLine).thenReturn(null);
		
		ReadUser ur = new ReadUser(brMock);
		ur.readFile();
	}
}