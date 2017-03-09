package de.eventon.ui;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.eventon.core.Event;
import de.eventon.services.BookingEventService;
import de.eventon.services.EventService;

@ManagedBean
@SessionScoped
public class BookingEventForm {

	private UUID bookingUUID;
	@ManagedProperty("#{bookingEventService}")
	private BookingEventService bookingEventService;
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	
	public BookingEventForm() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init(){
		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id = rqParameter.get("id");

		if (id != null) {
			try {
				int idAsInteger = Integer.parseInt(id);
				Optional<Event> optEvent = eventService.getEventById(idAsInteger);
				if(optEvent.isPresent())
				{
					Event event = optEvent.get();
					bookingUUID = bookingEventService.bookEvent(event);
				}
			} catch (NumberFormatException e) {
			}
		} 
	}
	
	public UUID getBookingUUID() {
		return bookingUUID;
	}

	public void setBookingUUID(UUID bookingUUID) {
		this.bookingUUID = bookingUUID;
	}

	public BookingEventService getBookingEventService() {
		return bookingEventService;
	}

	public void setBookingEventService(BookingEventService bookingEventService) {
		this.bookingEventService = bookingEventService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
