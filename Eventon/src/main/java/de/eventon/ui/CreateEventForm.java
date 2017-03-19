package de.eventon.ui;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Address;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;
import de.eventon.validator.event.EventValidator;

@Named("createEventForm")
@RequestScoped
/**
 * Diese Klasse dient sowohl für die Erstellung eines neuen Events als auch für
 * die Bearbeitung eines bereits bestehenden aber noch nicht veröffentlichten
 * Events.
 * 
 * @author Leon Stapper
 */
public class CreateEventForm implements Serializable {

	private static final long serialVersionUID = 8170899272566671596L;

	private UIComponent component;

	private String eventName;
	private String eventDate;
	private String eventTime;
	private String eventDescription;

	private String street;
	private String housenumber;
	private String zip;
	private String city;
	private String location;

	private Integer amountTicketsNormal;
	private Double priceTicketsNormal;
	private Integer amountTicketsPremium;
	private Double priceTicketsPremium;

	private boolean publish; // Event direkt veröffentlichen?

	private Event eventToEdit;
	
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String TIME_PATTERN = "HH:mm";

	@Inject
	private NavigationService navigationService;
	@Inject
	private EventService eventService;
	@Inject
	private ActiveUserService activeUserService;

	public CreateEventForm() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		// Wenn kein Nutzer eingeloggt ist bzw. dieser nicht Manager ist:
		// Redirect auf ErrorPage, da nur Manager ein Event erstellen/bearbeiten
		// dürfen
		User activeUser = activeUserService.getActiveUser();
		if (activeUser == null || !activeUser.isManager()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.userIsNotManager());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// Event erstellen oder bearbeiten?
			// Wenn ID im Query-Parameter: Bearbeiten
			// Bearbeitung nur wenn die ID gültig und das dazugehörige Event
			// unveröffentlicht ist
			// Wenn ID nicht im Query-Parameter: Erstellen (also nichts tun)
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
						// Ein bereits veröffentlichtes Event kann nicht mehr
						// bearbeitet werden
						if (!optEvent.get().isPublished()) {
							eventToEdit = optEvent.get();
							eventName = eventToEdit.getName();
							eventDate = eventToEdit.getDatetime().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
							eventTime = eventToEdit.getDatetime().format(DateTimeFormatter.ofPattern(TIME_PATTERN));
							eventDescription = eventToEdit.getDescription();

							street = eventToEdit.getAddress().getStreet();
							housenumber = eventToEdit.getAddress().getStreetnumber();
							zip = eventToEdit.getAddress().getZip();
							city = eventToEdit.getAddress().getCity();
							location = eventToEdit.getAddress().getLocationName();

							amountTicketsNormal = eventToEdit.getAmountTicketsNormal();
							priceTicketsNormal = eventToEdit.getPriceTicketsNormal();
							amountTicketsPremium = eventToEdit.getAmountTicketsPremium();
							priceTicketsPremium = eventToEdit.getPriceTicketsPremium();
						}
					}
				} catch (NumberFormatException e) {
				}

				// Ansonsten (wenn keine gültige ID mitgegeben wurde): Redirect
				// auf
				// ErrorPage, da das Event nicht gefunden werden kann
				if (eventToEdit == null) {
					try {
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect(navigationService.eventDoesNotExist());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public String create() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN + TIME_PATTERN);
		LocalDateTime dateTime = LocalDateTime.parse(eventDate + eventTime, formatter);

		if (EventValidator.validateDatetime(dateTime, component.getClientId(), "eventTime")
				&& EventValidator.validateAmountTickets(amountTicketsNormal, amountTicketsPremium)
				&& EventValidator.validatePrices(priceTicketsNormal, priceTicketsPremium)) {

			User eventCreator = activeUserService.getActiveUser();
			if (eventCreator != null && eventCreator.isManager()) {
				//Neuerstellen oder Bearbeiten?
				if(eventToEdit == null)
				{
					Address eventAddress = new Address(location, street, housenumber, zip, city);
					Event event = new Event(eventName, dateTime, eventDescription, amountTicketsNormal, priceTicketsNormal,
							amountTicketsPremium, priceTicketsPremium, eventAddress, eventCreator, publish);
	
					eventService.createEvent(event);
				} else {
					eventToEdit.setName(eventName);
					eventToEdit.setDatetime(dateTime);
					eventToEdit.setDescription(eventDescription);
					eventToEdit.setAmountTicketsNormal(amountTicketsNormal);
					eventToEdit.setAmountTicketsPremium(amountTicketsPremium);
					eventToEdit.setPriceTicketsNormal(priceTicketsNormal);
					eventToEdit.setPriceTicketsPremium(priceTicketsPremium);
					eventToEdit.getAddress().setStreet(street);
					eventToEdit.getAddress().setStreetnumber(housenumber);
					eventToEdit.getAddress().setZip(zip);
					eventToEdit.getAddress().setCity(city);
					eventToEdit.getAddress().setLocationName(location);
					eventToEdit.setPublished(publish);
				}
				return navigationService.createEventSuccessful();
			}
		}

		return navigationService.createEventFailed();
	}

	public String cancel() {
		return navigationService.cancelCreateEvent();
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAmountTicketsNormal() {
		return amountTicketsNormal;
	}

	public void setAmountTicketsNormal(Integer amountTicketsNormal) {
		this.amountTicketsNormal = amountTicketsNormal;
	}

	public Double getPriceTicketsNormal() {
		return priceTicketsNormal;
	}

	public void setPriceTicketsNormal(Double priceTicketsNormal) {
		this.priceTicketsNormal = priceTicketsNormal;
	}

	public Integer getAmountTicketsPremium() {
		return amountTicketsPremium;
	}

	public void setAmountTicketsPremium(Integer amountTicketsPremium) {
		this.amountTicketsPremium = amountTicketsPremium;
	}

	public Double getPriceTicketsPremium() {
		return priceTicketsPremium;
	}

	public void setPriceTicketsPremium(Double priceTicketsPremium) {
		this.priceTicketsPremium = priceTicketsPremium;
	}

	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public UIComponent getComponent() {
		return component;
	}

	public void setComponent(UIComponent component) {
		this.component = component;
	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public Event getEventToEdit() {
		return eventToEdit;
	}

	public void setEventToEdit(Event eventToEdit) {
		this.eventToEdit = eventToEdit;
	}
}
