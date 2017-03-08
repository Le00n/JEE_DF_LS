package de.eventon.core;

import java.time.LocalDateTime;

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
	
	//TODO Gegebenenfalls EventFactory einrichten?
	public Event() {
		
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
}
