package de.eventon.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {

	private int id;
	private String name;
	private LocalDateTime datetime;
	private String description;
	private double priceTicketsPremium;
	private double priceTicketsNormal;
	private int amountTicketsPremium;
	private int amountTicketsNormal;
	private Address address;
	private List<Booking> bookings;
	private User manager;
	private boolean published;
	private String filename;

	public Event(){
		bookings = new ArrayList<Booking>();
	}
	
	public Event(String name, LocalDateTime datetime, String description, int amountTicketsNormal,
			double priceTicketsNormal, int amountTicketsPremium, double priceTicketsPremium, Address address, 
			User manager, boolean published, String filename) {

		this.name = name;
		this.datetime = datetime;
		this.description = description;
		this.priceTicketsNormal = priceTicketsNormal;
		this.priceTicketsPremium = priceTicketsPremium;
		this.amountTicketsNormal = amountTicketsNormal;
		this.amountTicketsPremium = amountTicketsPremium;
		this.address = address;
		this.manager = manager;
		this.published = published;
		this.filename = filename;
		
		bookings = new ArrayList<Booking>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmountTicketsPremium() {
		return amountTicketsPremium;
	}

	public void setAmountTicketsPremium(int amountTicketsPremium) {
		this.amountTicketsPremium = amountTicketsPremium;
	}

	public int getAmountTicketsNormal() {
		return amountTicketsNormal;
	}

	public void setAmountTicketsNormal(int amountTicketsNormal) {
		this.amountTicketsNormal = amountTicketsNormal;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPriceTicketsPremium() {
		return priceTicketsPremium;
	}

	public void setPriceTicketsPremium(double priceTicketsPremium) {
		this.priceTicketsPremium = priceTicketsPremium;
	}

	public double getPriceTicketsNormal() {
		return priceTicketsNormal;
	}

	public void setPriceTicketsNormal(double priceTicketsNormal) {
		this.priceTicketsNormal = priceTicketsNormal;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public boolean addBooking(Booking booking) {
		return this.bookings.add(booking);
	}

	public int getAmountFreeNormalTickets() {
		int amountFreeNormalTickets = amountTicketsNormal;
		for (Booking b : bookings) {
			amountFreeNormalTickets -= b.getAmountNormalTickets();
		}
		
		return amountFreeNormalTickets;
	}

	public int getAmountFreePremiumTickets() {
		int amountFreePremiumTickets = amountTicketsPremium;
		for (Booking b : bookings) {
			amountFreePremiumTickets -= b.getAmountPremiumTickets();
		}

		return amountFreePremiumTickets;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getFilename() {
		if(filename == null){ 
			return "photo_not_available.jpg";
		}
		else {
			return filename;
		}
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
