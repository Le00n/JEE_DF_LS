package de.eventon.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event {

	@Id @GeneratedValue @Column(nullable=false)
	private int eventId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private LocalDateTime datetime;
	@Column(nullable=false)
	private String description;
	@Column(nullable=false)
	private double priceTicketsPremium;
	@Column(nullable=false)
	private double priceTicketsNormal;
	@Column(nullable=false)
	private int amountTicketsPremium;
	@Column(nullable=false)
	private int amountTicketsNormal;
	@ManyToOne
	@JoinColumn(name="addressId", nullable=false)
	private Address address;
	@OneToMany(mappedBy="event")
	private List<Booking> bookings;
	@ManyToOne
	@JoinColumn(name="userId", nullable=false)
	private User manager;
	@Column(nullable=false)
	private boolean published;
	@Column
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

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
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
			return "/resources/img/photo_not_available.jpg";
		}
		else {
			return "/images/" + filename;
		}
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
