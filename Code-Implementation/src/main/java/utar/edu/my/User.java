package utar.edu.my;

public class User implements IUser {
	private String ID;
	private String name;
	private String email;
	private String phoneNo;

	// Constructor
	public User() {
	}
	
	public User(String ID, String name, String email, String phoneNo) {
		this.ID = ID;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	
	// Get Functions
	public String getID() {
        return ID;
    }
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	// Set Functions
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	// Base Functions
	@Override
	public String toString() {
		return ID + ";" + name + ";" + email + ";" + phoneNo;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		
		if (o == null || getClass() != o.getClass()) 
			return false;
		
		User otherUser = (User) o;
		return toString().equals(otherUser.toString());
	}
	
	@Override
	public int hashCode() {
		return ID != null ? ID.hashCode() : 0;
	}
}