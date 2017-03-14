package de.eventon.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.eventon.core.Address;
import de.eventon.core.Booking;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;

@ManagedBean
@RequestScoped
public class ManagerOverviewEventsInProcess {

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;

	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;
	
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	
	public List<Event> getEvents(){
		if(eventService != null)
		{
			User manager = activeUserService.getActiveUser();
			List<Event> events = eventService.getEvents().stream()
					.filter(event -> !event.isPublished() && event.getManager().getId() == manager.getId())
					.collect(Collectors.toList());
			return events;
		}
		return null;
	}
	
	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
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
