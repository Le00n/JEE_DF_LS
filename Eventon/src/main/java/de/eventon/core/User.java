package de.eventon.core;

import java.util.ArrayList;
import java.util.List;

public class User {

	private int id;
	private String email;
	private String hashedPassword;
	private String firstname;
	private String lastname;
	private Address address;
	private BankAccount	bankAccount;
	private List<Booking> bookings;
	private boolean manager;
	
	public User(String email, String hashedPassword, String firstname, String lastname, Address address, BankAccount bankAccount, boolean manager) {
		bookings = new ArrayList<Booking>();
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.bankAccount = bankAccount;
		this.manager = manager;
	}

	public boolean addBooking(Booking booking){
		return bookings.add(booking);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	
	public void setBankAccount(BankAccount bankAccount){
		this.bankAccount = bankAccount;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}
}
