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
import de.eventon.services.impl.LoginService;
import de.eventon.services.interfaces.IsEventService;
import de.eventon.services.interfaces.IsLoginService;
import de.eventon.services.interfaces.IsNavigationService;
import de.eventon.session.SessionContext;

@Named("managerOverviewEvents")
@RequestScoped
public class ManagerOverviewEvents implements Serializable{

	private static final long serialVersionUID = -936830754297682305L;
	
	@Inject
	private SessionContext sessionContext;
	@Inject
	private IsNavigationService navigationService;
	@Inject
	private IsEventService eventService;
	
	public ManagerOverviewEvents(){
	}
	
	@PostConstruct
	private void init(){
		// Ansonsten (wenn keine gültige ID mitgegeben wurde): Redirect auf
		// ErrorPage, da das Event nicht gefunden werden kann
		if (sessionContext.getActiveUser() == null || !sessionContext.getActiveUser().isManager()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.notAuthorizedViewingManagerSites());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Event> getUnpublishedEvents(){
		if(eventService != null && sessionContext.getActiveUser() != null && sessionContext.getActiveUser().isManager())
		{
			User manager = sessionContext.getActiveUser();
			List<Event> events = eventService.getEvents().stream()
					.filter(event -> !event.isPublished() && event.getManager().getUserId() == manager.getUserId())
					.collect(Collectors.toList());
			return events;
		}
		return null;
	}
	public List<Event> getPublishedEvents(){
		if(eventService != null && sessionContext.getActiveUser() != null && sessionContext.getActiveUser().isManager())
		{
			User manager = sessionContext.getActiveUser();
			List<Event> events = eventService.getEvents().stream()
					.filter(event -> event.isPublished() && event.getManager().getUserId() == manager.getUserId())
					.collect(Collectors.toList());
			return events;
		}
		return null;
	}
	
	public SessionContext getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	public IsNavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(IsNavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public IsEventService getEventService() {
		return eventService;
	}

	public void setEventService(IsEventService eventService) {
		this.eventService = eventService;
	}
}
