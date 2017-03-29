package de.eventon.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Event;
import de.eventon.services.interfaces.IsEventBookingService;
import de.eventon.services.interfaces.IsEventService;
import de.eventon.services.interfaces.IsNavigationService;
import de.eventon.session.SessionContext;

@Named("eventBookingForm")
@ViewScoped // Muss für die Ansicht sichtbar sein (Request reicht nicht: ist bei
			// Buchung schon ungültig; Session zu viel: bei zweiter Buchung ist
			// die erste noch hinterlegt)
public class EventBookingForm implements Serializable {

	private static final long serialVersionUID = -4758939465775969668L;

	private Integer amountTicketsNormal;
	private Integer amountTicketsPremium;
	private Event event;
	private UUID bookingUUID;
	private boolean bookingConfirmed;

	@Inject
	private SessionContext sessionContext;
	@Inject
	private IsEventService eventService;
	@Inject
	private IsEventBookingService eventBookingService;
	@Inject
	private IsNavigationService navigationService;

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
		// Separate Variablen, um zu verhindern, dass im Anschluss im
		// Input-Field "0" steht
		// Null wird somit automatisch als leeres Feld dargestellt
		int intAmountTicketsNormal = (amountTicketsNormal == null) ? 0 : amountTicketsNormal.intValue();
		int intAmountTicketsPremium = (amountTicketsPremium == null) ? 0 : amountTicketsPremium.intValue();

		if(sessionContext.getActiveUser() != null){
			//Mindestens 1 Ticket muss gebucht werden
			if (intAmountTicketsNormal != 0 || intAmountTicketsPremium != 0) {
				//Steht die gewünschte Anzahl noch zur Verfügung?
				if (intAmountTicketsNormal <= event.getAmountFreeNormalTickets()) {
					if(intAmountTicketsPremium <= event.getAmountFreePremiumTickets()) {
						
						//Alle Bedingung erfüllt --> Buchen
						Optional<UUID> optBookingUUID = eventBookingService.bookEvent(event, intAmountTicketsNormal,
								intAmountTicketsPremium);
						if (optBookingUUID.isPresent()) {
							bookingUUID = optBookingUUID.get();
							setBookingConfirmed(true);
							return navigationService.book();
						}
					} else {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Es stehen keine " + intAmountTicketsPremium + " Logen-Tickets zur Verfügung.", "Es stehen keine " + intAmountTicketsPremium + " Logen-Tickets zur Verfügung.");
						FacesContext.getCurrentInstance().addMessage("eventBookingForm:inputPremium", msg);
					}
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Es stehen keine " + intAmountTicketsNormal + " Parkett-Tickets zur Verfügung.", "Es stehen keine " + intAmountTicketsNormal + " Parkett-Tickets zur Verfügung.");
					FacesContext.getCurrentInstance().addMessage("eventBookingForm:inputNormal", msg);
				}
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Es muss mindestens ein Ticket gebucht werden.", "Es muss mindestens ein Ticket gebucht werden.");
				FacesContext.getCurrentInstance().addMessage("eventBookingForm:inputNormal", msg);
				FacesContext.getCurrentInstance().addMessage("eventBookingForm:inputPremium", msg);
			}
		} else {
			return navigationService.bookWithoutLogin(event.getEventId());
		}
		
		setBookingConfirmed(false);
		return navigationService.bookingFailed();
	}

	public String bookingCodeSeen() {
		return navigationService.bookingCodeSeen();
	}

	public String cancel() {
		return navigationService.cancelBooking();
	}

	public IsEventService getEventService() {
		return eventService;
	}

	public void setEventService(IsEventService eventService) {
		this.eventService = eventService;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public IsNavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(IsNavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public IsEventBookingService getEventBookingService() {
		return eventBookingService;
	}

	public void setEventBookingService(IsEventBookingService eventBookingService) {
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
