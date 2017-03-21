package de.eventon.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.EventService;
import de.eventon.services.NavigationService;

@Named("managerOverviewEvents")
@RequestScoped
public class ManagerOverviewEvents implements Serializable{

	private static final long serialVersionUID = -936830754297682305L;
	
	@Inject
	private ActiveUserService activeUserService;
	@Inject
	private NavigationService navigationService;
	@Inject
	private EventService eventService;
	
	public ManagerOverviewEvents(){
	}
	
	@PostConstruct
	private void init(){
		// Ansonsten (wenn keine gültige ID mitgegeben wurde): Redirect auf
		// ErrorPage, da das Event nicht gefunden werden kann
		if (activeUserService.getActiveUser() == null || !activeUserService.getActiveUser().isManager()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.notAuthorizedViewingManagerSites());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Event> getUnpublishedEvents(){
		if(eventService != null && activeUserService.getActiveUser() != null && activeUserService.getActiveUser().isManager())
		{
			User manager = activeUserService.getActiveUser();
			List<Event> events = eventService.getEvents().stream()
					.filter(event -> !event.isPublished() && event.getManager().getUserId() == manager.getUserId())
					.collect(Collectors.toList());
			return events;
		}
		return null;
	}
	public List<Event> getPublishedEvents(){
		if(eventService != null && activeUserService.getActiveUser() != null && activeUserService.getActiveUser().isManager())
		{
			User manager = activeUserService.getActiveUser();
			List<Event> events = eventService.getEvents().stream()
					.filter(event -> event.isPublished() && event.getManager().getUserId() == manager.getUserId())
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
