package de.eventon.services;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class BookingEventService {

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;
	
	public String bookEvent(){
//		String userEmail = activeUserService.getActiveUser().getEmail();
//		int eventId = event.getId();
//		LocalDateTime bookingDatetime = LocalDateTime.now();
//		
//		UUID uuid = getUUID(userEmail, eventId, bookingDatetime);
//		System.out.println(uuid.toString());
		return "#";
	}
	
	private UUID getUUID(String userEmail, int eventId, LocalDateTime bookingDatetime){
		String accumulatedTerm = userEmail + eventId + bookingDatetime.toString();
		return UUID.fromString(accumulatedTerm);
	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}
}
