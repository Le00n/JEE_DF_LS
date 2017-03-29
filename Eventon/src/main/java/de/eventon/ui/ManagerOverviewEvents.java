package de.eventon.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsEventService;
import de.eventon.services.interfaces.IsNavigationService;
import de.eventon.session.SessionContext;

@Named("managerOverviewEvents")
@RequestScoped
public class ManagerOverviewEvents implements Serializable {

	private static final long serialVersionUID = -936830754297682305L;

	@Inject
	private SessionContext sessionContext;
	@Inject
	private IsNavigationService navigationService;
	@Inject
	private IsEventService eventService;

	private HashMap<Event, Boolean> mapEventPublished;
	private Event event;

	public ManagerOverviewEvents() {
	}

	@PostConstruct
	private void init() {
		// Ansonsten (wenn keine gültige ID mitgegeben wurde): Redirect auf
		// ErrorPage, da das Event nicht gefunden werden kann
		if (sessionContext.getActiveUser() == null || !sessionContext.getActiveUser().isManager()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(navigationService.notAuthorizedViewingManagerSites());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String eventId = rqParameter.get("id");

		// Wurde eine gültige ID im Query-Parameter mitgegeben?
		// Dann Event anzeigen
		if (eventId != null) {
			try {
				int idAsInteger = Integer.parseInt(eventId);
				Optional<Event> optEvent = eventService.getEventById(idAsInteger);
				if (optEvent.isPresent()) {
					setEvent(optEvent.get());
				}
			} catch (NumberFormatException e) {
				setEvent(null);
			}
		} else {
			setEvent(null);
		}
	}

	public List<Event> getPublishedEvents() {
		User manager = sessionContext.getActiveUser();
		Optional<List<Event>> events = eventService.getManagerEvents(manager, true);
		if(!events.isPresent())
			return new ArrayList<Event>();
		else
			return events.get();
	}

	public String publishEvents() {
		for (Entry<Event, Boolean> map : mapEventPublished.entrySet()) {
			if (map.getValue()) {
				eventService.publishEvent(map.getKey());
			}
		}
		return navigationService.managerOverviewEventsInProcess();
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

	public HashMap<Event, Boolean> getMapEventPublished() {
		User manager = sessionContext.getActiveUser();
		Optional<List<Event>> events = eventService.getManagerEvents(manager, false);
		mapEventPublished = new HashMap<>();
		if(events.isPresent())
		{
			for (Event event : events.get()) {			
				mapEventPublished.put(event, event.isPublished());
			}
		}
		return mapEventPublished;
	}

	public void setMapEventPublished(HashMap<Event, Boolean> mapEventPublished) {
		this.mapEventPublished = mapEventPublished;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
