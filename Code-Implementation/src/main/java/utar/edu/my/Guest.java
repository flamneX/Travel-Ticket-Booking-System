package utar.edu.my;

public class Guest implements IUser {
	private String name;
	private String email;
	private String phoneNo;

	public Guest(String name, String email, String phoneNo) {
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	// Get Methods
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	// Set Methods
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
		return name + ";" + email + ";" + phoneNo;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		
		if (o == null || getClass() != o.getClass()) 
			return false;
		
		Guest otherGuest = (Guest) o;
		return toString().equals(otherGuest.toString());
	}
	
	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}