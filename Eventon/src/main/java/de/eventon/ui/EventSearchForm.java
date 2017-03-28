package de.eventon.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Event;
import de.eventon.services.interfaces.IsEventService;
import de.eventon.services.interfaces.IsNavigationService;

@Named("eventSearchForm")
@SessionScoped
public class EventSearchForm implements Serializable {

	private static final long serialVersionUID = 549403592893015555L;
	
	private String searchTerm;
	private List<Event> searchedEvents;

	@Inject
	private IsNavigationService navigationService;
	@Inject
	private IsEventService eventService;

	public EventSearchForm() {
		searchedEvents = new ArrayList<Event>();
	}

	public String searchEvents() {
		Optional<List<Event>> optSearchedEvents = eventService.searchEvents(searchTerm);
		if (optSearchedEvents.isPresent()) {
			searchedEvents = optSearchedEvents.get();
		} else {
			searchedEvents = new ArrayList<Event>();
		}
		
		return navigationService.searchEvents();
	}

	public List<Event> getSearchedEvents() {
		return searchedEvents;
	}

	public void setSearchedEvents(List<Event> searchedEvents) {
		this.searchedEvents = searchedEvents;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public IsNavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(IsNavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public IsEventService getEventSearchService() {
		return eventService;
	}

	public void setEventSearchService(IsEventService eventService) {
		this.eventService = eventService;
	}
}
