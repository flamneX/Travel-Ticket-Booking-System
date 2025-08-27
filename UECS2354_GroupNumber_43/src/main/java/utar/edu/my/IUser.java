package utar.edu.my;

public interface IUser {
	// Get Functions
    public String getName();
    public String getEmail();
    public String getPhoneNo();

    // Set Functions
    public void setName(String name);
    public void setEmail(String email);
    public void setPhoneNo(String phoneNo);
}