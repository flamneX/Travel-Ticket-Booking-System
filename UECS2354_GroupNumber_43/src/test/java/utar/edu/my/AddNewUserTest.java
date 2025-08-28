package utar.edu.my;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import java.io.IOException;

@RunWith(JUnitParamsRunner.class)
public class AddNewUserTest {
	// Initiate Mocked BufferedWriter
	private BufferedWriter bwMock = mock(BufferedWriter.class);

	// Valid Values
	private Object getValidWriteFileParams() {
		return new Object[] {
			new Object[] {new User("USER001", "RUI", "rui@gmail.com", "0123456789"), "USER001;RUI;rui@gmail.com;0123456789"},
			new Object[] {new User("USER002", "AKITO", "akito@gmail.com", "0123456789"), "USER002;AKITO;akito@gmail.com;0123456789"}
		};
	}
	
	@Test
	@Parameters (method = "getValidWriteFileParams")
	public void testWriteFileValidValues(IUser newUser, String expectedResult) throws IOException {
		
		AddNewUser anu = new AddNewUser(bwMock);
		anu.WriteFile(newUser);
		
		verify(bwMock, times(1)).write(expectedResult);
	}
	
	// IOException Values
	private Object getIOExceptionWriteFileParams() {
		return new Object[] {
			new Object[] {new User("USER001", "RUI", "rui@gmail.com", "0123456789")},
			new Object[] {new User("USER002", "AKITO", "akito@gmail.com", "0123456789")}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getIOExceptionWriteFileParams")
	public void testWriteFileIOException(IUser newUser) throws IOException {
		
		BufferedWriter bwMock = mock(BufferedWriter.class);
		doThrow(new IOException()).when(bwMock).write(newUser.toString());
		
		AddNewUser anu = new AddNewUser(bwMock);
		anu.WriteFile(newUser);
	}

	// Invalid Values
	private Object getInvalidWriteFileParams() {
		return new Object[] {
			new Object[] {null}
		};
	}
		
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidWriteFileParams")
	public void testWriteFileInvalidValues(IUser newUser) throws IOException {
			
		BufferedWriter bwMock = mock(BufferedWriter.class);
		
		AddNewUser anu = new AddNewUser(bwMock);
		anu.WriteFile(newUser);
	}
}
