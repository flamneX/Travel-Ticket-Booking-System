package utar.edu.my;

import static org.mockito.Mockito.*;
import org.junit.Test;
import java.io.IOException;


public class ReadUserTest {
	AddNewUser newUser = new AddNewUser(); 
	User dummyUser = new User("123", "123", "123", "123");

	@Test
	public void test() throws IOException {
		
		
		AddNewUser spy = spy(newUser);
		spy.WriteFile(dummyUser);
		
		verify(spy, times(1)).WriteFile(dummyUser);
	}
	
	@Test (expected = IOException.class)
	public void test2() throws IOException{
		AddNewUser spy = spy(newUser);
		
		doThrow(new IOException()).when(spy).WriteFile(dummyUser);
		
		spy.WriteFile(dummyUser);
	}
}
