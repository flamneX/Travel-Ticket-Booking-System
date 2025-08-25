package main.java.domain;

public class user {
    private String name;
    private final String ID;
    private String email;
    private String phoneNo;

    public user(String name, String ID, String email, String phoneNo) {
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    // Get Functions
    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
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
        return name + ";" + ID + ";" + email + ";" + phoneNo;
    }
}
