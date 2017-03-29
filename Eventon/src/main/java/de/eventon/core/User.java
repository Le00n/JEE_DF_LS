package de.eventon.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue
	@Column(name="USERID")
	private int userId;
	@Column
	private String email;
	@Column
	private String hashedPassword;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@ManyToOne
	private Address address;
	@ManyToOne
	private BankAccount bankAccount;
	@OneToMany(mappedBy="user")
	private List<Booking> bookings;
	@Column
	private boolean manager;

	public User() {
	}

	public User(String email, String hashedPassword, String firstname, String lastname, Address address,
			BankAccount bankAccount, boolean manager) {
		bookings = new ArrayList<Booking>();
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.bankAccount = bankAccount;
		this.manager = manager;
	}

	public boolean addBooking(Booking booking) {
		return bookings.add(booking);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int UserId) {
		this.userId = UserId;
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

	public boolean validatePassword(String hashedPassword) {
		if (hashedPassword != null) {
			return this.hashedPassword.equals(hashedPassword);
		} else {
			return false;
		}
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

	public void setBankAccount(BankAccount bankAccount) {
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
