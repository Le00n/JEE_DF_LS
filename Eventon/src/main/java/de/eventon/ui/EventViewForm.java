package de.eventon.ui;

import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.eventon.core.Event;
import de.eventon.services.BookingEventService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;

@ManagedBean
@SessionScoped
public class EventViewForm {

	private int bookNormalTickets;
	private int bookPremiumTickets;
	private Event event;
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	@ManagedProperty("#{bookingEventService}")
	private BookingEventService bookingEventService;
	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;
	
	public EventViewForm() {
		
	}

	@PostConstruct
	private void init(){
		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id = rqParameter.get("id");

		if (id != null) {
			try {
				int idAsInteger = Integer.parseInt(id);
				Optional<Event> optEvent = eventService.getEventById(idAsInteger);
				if(optEvent.isPresent())
				{
					event = optEvent.get();
				}
			} catch (NumberFormatException e) {
				event = null;
			}
		} else {
			event = null;
		}
	}
	
	public String book(){
		System.out.println("booooook");
		return navigationService.book();
	}
	
	public String cancel(){
		return navigationService.cancelBooking();
	}
	
	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	

	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public BookingEventService getBookingEventService() {
		return bookingEventService;
	}

	public void setBookingEventService(BookingEventService bookingEventService) {
		this.bookingEventService = bookingEventService;
	}

	public int getBookNormalTickets() {
		return bookNormalTickets;
	}

	public void setBookNormalTickets(int bookNormalTickets) {
		this.bookNormalTickets = bookNormalTickets;
	}

	public int getBookPremiumTickets() {
		return bookPremiumTickets;
	}

	public void setBookPremiumTickets(int bookPremiumTickets) {
		this.bookPremiumTickets = bookPremiumTickets;
	}
}
