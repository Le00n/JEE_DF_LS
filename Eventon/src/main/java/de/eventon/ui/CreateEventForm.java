package de.eventon.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import de.eventon.core.Address;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;
import de.eventon.validator.event.EventValidator;

@Named("createEventForm")
@RequestScoped
public class CreateEventForm implements Serializable{

	private static final long serialVersionUID = 8170899272566671596L;

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

	private boolean publish;

	private Integer amountTicketsNormal;
	private Double priceTicketsNormal;
	private Integer amountTicketsPremium;
	private Double priceTicketsPremium;

	private Part file;
	
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
		// Redirect auf ErrorPage, da nur Manager ein Event erstellen dürfen
		User activeUser = activeUserService.getActiveUser();
		if (activeUser == null || !activeUser.isManager()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.userIsNotManager());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String create() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(eventDate + eventTime, formatter);

		if (EventValidator.validateDatetime(dateTime, component.getClientId(), "eventTime")
				&& EventValidator.validateAmountTickets(amountTicketsNormal, amountTicketsPremium)
				&& EventValidator.validatePrices(priceTicketsNormal, priceTicketsPremium)) {
			
			
			String filename = getFileName(getFile());
			Path destination = null;
			try{
				System.out.println("vor Aenderung");
				String filenameWithoutEnding = filename.substring(0, filename.lastIndexOf("."));
				String fileEnding = filename.substring(filename.lastIndexOf("."));
				destination = Files.createTempFile(Paths.get("/var/webapp/images"), filenameWithoutEnding, fileEnding);
				filename = destination.getFileName().toString();
				System.out.println("File: " + destination);
			}catch(Exception ex){ ex.printStackTrace(); }
			
			
			
			InputStream bytes = null;
			if(getFile() != null)
			{
				try{
				bytes = getFile().getInputStream();
				Files.copy(bytes, destination, StandardCopyOption.REPLACE_EXISTING);
				}catch(Exception e)
				{
					System.out.println("File Exception");
					e.printStackTrace();
				}
			}
			
			User eventCreator = activeUserService.getActiveUser();
			if (eventCreator != null && eventCreator.isManager()) {
				Address eventAddress = new Address(location, street, housenumber, zip, city);
				Event event = new Event(eventName, dateTime, eventDescription, amountTicketsNormal, priceTicketsNormal,
						amountTicketsPremium, priceTicketsPremium, eventAddress, eventCreator, publish, filename);

				eventService.createEvent(event);
				System.out.println("Event filename: " + event.getFilename());
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

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	private static String getFileName(Part filePart)
    {
        String header = filePart.getHeader("content-disposition");
        if(header == null)
            return null;
        for(String headerPart : header.split(";"))
        {
            if(headerPart.trim().startsWith("filename"))
            {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
