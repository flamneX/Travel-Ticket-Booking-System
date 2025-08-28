package utar.edu.my;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
	User user = new User("123", "123", "123", "123");

	@Test
	public void testGetID() {
		String result = user.getID();
		assertEquals("123", result);
	}
	
	@Test
	public void testGetName() {
		String result = user.getName();
		assertEquals("123", result);
	}
	
	@Test
	public void testGetEmail() {
		String result = user.getEmail();
		assertEquals("123", result);
	}
	
	@Test
	public void testGetPhoneNo() {
		String result = user.getPhoneNo();
		assertEquals("123", result);
	}

}
