package de.eventon.core;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Booking {

	@Id
	private UUID bookingUUID;
	@Column
	private LocalDateTime bookingDatetime;
	@OneToOne
	private Event event;
	@Column
	private int amountNormalTickets;
	@Column
	private int amountPremiumTickets;

	public Booking() {
		// TODO Auto-generated constructor stub
	}
	
	public Booking(Event event, int amountNormalTickets, int amountPremiumTickets) {
		setBookingUUID(UUID.randomUUID());
		setBookingDatetime(LocalDateTime.now());
		setEvent(event);
		setAmountNormalTickets(amountNormalTickets);
		setAmountPremiumTickets(amountPremiumTickets);
	}
	
	public UUID getBookingUUID() {
		return bookingUUID;
	}

	public void setBookingUUID(UUID bookingUUID) {
		this.bookingUUID = bookingUUID;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getAmountNormalTickets() {
		return amountNormalTickets;
	}

	public void setAmountNormalTickets(int amountNormalTickets) {
		this.amountNormalTickets = amountNormalTickets;
	}

	public int getAmountPremiumTickets() {
		return amountPremiumTickets;
	}

	public void setAmountPremiumTickets(int amountPremiumTickets) {
		this.amountPremiumTickets = amountPremiumTickets;
	}

	public LocalDateTime getBookingDatetime() {
		return bookingDatetime;
	}

	public void setBookingDatetime(LocalDateTime bookingDatetime) {
		this.bookingDatetime = bookingDatetime;
	}
	
	public double getPrice(){
		if(event == null)
		{
			return 0;
		} else 
		{
			return event.getPriceTicketsNormal() * getAmountNormalTickets() + event.getPriceTicketsPremium() * getAmountPremiumTickets();
		}
	}
}
