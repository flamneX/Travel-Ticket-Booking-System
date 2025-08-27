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
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPhoneNo() {
		return phoneNo;
	}

	// Set Methods
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	// Base Functions
	@Override
	public String toString() {
		return name + ";" + email + ";" + phoneNo;
	}
}
