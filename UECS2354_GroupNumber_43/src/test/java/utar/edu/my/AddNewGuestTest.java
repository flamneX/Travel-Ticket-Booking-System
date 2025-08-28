package utar.edu.my;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

public class AddNewGuestTest {

	@Test
	public void testWriteFile() {
		Path guestPath = mock(Path.class);
		
		AddNewGuest ang = new AddNewGuest(guestPath);
		
	}

}
