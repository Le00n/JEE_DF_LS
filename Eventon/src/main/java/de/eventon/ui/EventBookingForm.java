package de.eventon.ui;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import de.eventon.core.Event;
import de.eventon.services.EventBookingService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;

@ManagedBean
@ViewScoped // Muss f�r die Ansicht sichtbar sein (Request reicht nicht: ist bei
			// Buchung schon ung�ltig; Session zu viel: bei zweiter Buchung ist
			// die erste noch hinterlegt)
public class EventBookingForm {

	private int amountTicketsNormal;
	private int amountTicketsPremium;
	private Event event;
	private UUID bookingUUID;
	private boolean bookingWanted;

	@ManagedProperty("#{eventService}")
	private EventService eventService;

	@ManagedProperty("#{eventBookingService}")
	private EventBookingService eventBookingService;

	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;

	public EventBookingForm() {

	}

	@PostConstruct
	private void init() {
		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String id = rqParameter.get("id");

		// Wurde eine g�ltige ID im Query-Parameter mitgegeben?
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

		// Ansonsten (wenn keine g�ltige ID mitgegeben wurde): Redirect auf
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
		setBookingWanted(true);

		Optional<UUID> optBookingUUID = eventBookingService.bookEvent(event, amountTicketsNormal, amountTicketsPremium);
		if (optBookingUUID.isPresent()) {
			bookingUUID = optBookingUUID.get();
		}
		return navigationService.book();
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

	public int getAmountTicketsNormal() {
		return amountTicketsNormal;
	}

	public void setAmountTicketsNormal(int amountTicketsNormal) {
		this.amountTicketsNormal = amountTicketsNormal;
	}

	public int getAmountTicketsPremium() {
		return amountTicketsPremium;
	}

	public void setAmountTicketsPremium(int amountTicketsPremium) {
		this.amountTicketsPremium = amountTicketsPremium;
	}

	public boolean isBookingWanted() {
		return bookingWanted;
	}

	public void setBookingWanted(boolean bookingWanted) {
		this.bookingWanted = bookingWanted;
	}
}