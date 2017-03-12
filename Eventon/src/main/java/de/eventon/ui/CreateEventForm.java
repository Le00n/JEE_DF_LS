package de.eventon.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.eventon.services.EventService;
import de.eventon.services.NavigationService;

@ManagedBean
@RequestScoped
public class CreateEventForm {

	private String eventName;
	private String eventDescription;

	private int eventStartHour;
	private int eventStartMinute;

	private String street;
	private String streetnumber;
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
		eventService.createEvent(eventName, eventDescription, amountTicketsNormal, priceTicketsNormal,
				amountTicketsPremium, priceTicketsPremium, location, street, streetnumber, zip, city);
		return navigationService.createEventSuccessful();
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

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
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
}
