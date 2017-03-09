package de.eventon.services;

import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.eventon.core.Booking;
import de.eventon.core.Event;
import de.eventon.core.User;

@SessionScoped
@ManagedBean
public class BookingEventService {

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;
	
	public BookingEventService() {
	}
	
	public UUID bookEvent(Event event){
		User user = activeUserService.getActiveUser();
		int eventId = event.getId();
		
//		Booking booking = new Booking(event, );
//		user.addBooking(booking);
		return UUID.randomUUID();
	}
	
//	private UUID getUUID(String userEmail, int eventId, LocalDateTime bookingDatetime){
//		String accumulatedTerm = userEmail + eventId + bookingDatetime.toString();
//		return UUID.fromString(accumulatedTerm);
//	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}
}
