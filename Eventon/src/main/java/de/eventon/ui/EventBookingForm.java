package de.eventon.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Event;
import de.eventon.services.EventBookingService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;

@Named("eventBookingForm")
@ViewScoped // Muss für die Ansicht sichtbar sein (Request reicht nicht: ist bei
			// Buchung schon ungültig; Session zu viel: bei zweiter Buchung ist
			// die erste noch hinterlegt)
public class EventBookingForm implements Serializable{

	private static final long serialVersionUID = -4758939465775969668L;
	
	private Integer amountTicketsNormal;
	private Integer amountTicketsPremium;
	private Event event;
	private UUID bookingUUID;
	private boolean bookingConfirmed;

	@Inject
	private EventService eventService;
	@Inject
	private EventBookingService eventBookingService;
	@Inject
	private NavigationService navigationService;

	public EventBookingForm() {

	}

	@PostConstruct
	private void init() {
		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String id = rqParameter.get("id");

		// Wurde eine gültige ID im Query-Parameter mitgegeben?
		// Dann Event anzeigen
		if (id != null) {
			try {
				int idAsInteger = Integer.parseInt(id);
				Optional<Event> optEvent = eventService.getEventById(idAsInteger);
				if (optEvent.isPresent()) {
					event = optEvent.get();
				}
			} catch (NumberFormatException e) {
				event = null;
			}
		} else {
			event = null;
		}

		// Ansonsten (wenn keine gültige ID mitgegeben wurde): Redirect auf
		// ErrorPage, da das Event nicht gefunden werden kann
		if (event == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.eventDoesNotExist());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String book() {
		setBookingConfirmed(true);

		Optional<UUID> optBookingUUID = eventBookingService.bookEvent(event, amountTicketsNormal, amountTicketsPremium);
		if (optBookingUUID.isPresent()) {
			bookingUUID = optBookingUUID.get();
		}
		return navigationService.book();
	}
	
	public String bookingCodeSeen(){
		return navigationService.bookingCodeSeen();
	}

	public String cancel() {
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

	public EventBookingService getEventBookingService() {
		return eventBookingService;
	}

	public void setEventBookingService(EventBookingService eventBookingService) {
		this.eventBookingService = eventBookingService;
	}

	public UUID getBookingUUID() {
		return bookingUUID;
	}

	public void setBookingUUID(UUID bookingUUID) {
		this.bookingUUID = bookingUUID;
	}

	public Integer getAmountTicketsNormal() {
		return amountTicketsNormal;
	}

	public void setAmountTicketsNormal(Integer amountTicketsNormal) {
		this.amountTicketsNormal = amountTicketsNormal;
	}

	public Integer getAmountTicketsPremium() {
		return amountTicketsPremium;
	}

	public void setAmountTicketsPremium(Integer amountTicketsPremium) {
		this.amountTicketsPremium = amountTicketsPremium;
	}

	public boolean isBookingConfirmed() {
		return bookingConfirmed;
	}

	public void setBookingConfirmed(boolean bookingConfirmed) {
		this.bookingConfirmed = bookingConfirmed;
	}
}
