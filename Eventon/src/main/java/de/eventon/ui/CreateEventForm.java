package de.eventon.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import de.eventon.core.Address;
import de.eventon.core.Event;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;
import de.eventon.validator.address.AddressValidator;
import de.eventon.validator.event.EventValidator;

@ManagedBean
@RequestScoped
public class CreateEventForm {

	private UIComponent component;
	
	private String eventName;
	private String eventDate;
	private String eventTime;
	private String eventDescription;

	private int eventStartHour;
	private int eventStartMinute;

	private String street;
	private String housenumber;
	private String zip;
	private String city;
	private String location;

	private Integer amountTicketsNormal;
	private Double priceTicketsNormal;
	private Integer amountTicketsPremium;
	private Double priceTicketsPremium;

	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;
	@ManagedProperty("#{eventService}")
	private EventService eventService;

	public CreateEventForm() {
		// TODO Auto-generated constructor stub
	}

	public String create() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(eventDate + eventTime, formatter);

		if (EventValidator.validateDatetime(dateTime, component.getClientId(), "eventTime")
				&& EventValidator.validateAmountTickets(amountTicketsNormal, amountTicketsPremium)
				&& EventValidator.validatePrices(priceTicketsNormal, priceTicketsPremium)) {
			
			if (AddressValidator.validateAddress(location, street, housenumber, zip, city)) {
				Address eventAddress = new Address(location, street, housenumber, zip, city);
				Event event = new Event(eventName, dateTime, eventDescription, amountTicketsNormal, priceTicketsNormal,
						amountTicketsPremium, priceTicketsPremium, eventAddress);

				eventService.createEvent(event);
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

	public int getEventStartHour() {
		return eventStartHour;
	}

	public void setEventStartHour(int eventStartHour) {
		this.eventStartHour = eventStartHour;
	}

	public int getEventStartMinute() {
		return eventStartMinute;
	}

	public void setEventStartMinute(int eventStartMinute) {
		this.eventStartMinute = eventStartMinute;
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
}
