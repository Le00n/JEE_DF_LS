package de.eventon.ui;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;

@Named("managerOverviewEventsInProcess")
@RequestScoped
public class ManagerOverviewEventsInProcess implements Serializable{

	private static final long serialVersionUID = -936830754297682305L;
	
	@Inject
	private ActiveUserService activeUserService;
	@Inject
	private NavigationService navigationService;
	@Inject
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
