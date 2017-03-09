package de.eventon.core;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

	private UUID bookingUUID;
	private LocalDateTime bookingDatetime;
	private Event event;
	private int amountNormalTickets;
	private int amountPremiumTickets;

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
}
