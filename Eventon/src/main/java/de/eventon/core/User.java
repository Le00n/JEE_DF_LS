package de.eventon.core;

public class User {

	private String email;
	private String hashedPassword;
	private String firstname;
	private String lastname;
	private Address address;
	
	public User(String email, String hashedPassword, String firstname, String lastname, Address address) {
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
