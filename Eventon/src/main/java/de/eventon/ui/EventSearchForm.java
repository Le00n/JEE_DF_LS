package de.eventon.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Event;
import de.eventon.services.NavigationService;
import de.eventon.services.interfaces.IsEventService;

@Named("eventSearchForm")
@SessionScoped
public class EventSearchForm implements Serializable {

	private static final long serialVersionUID = 549403592893015555L;
	
	private String searchTerm;
	private List<Event> searchedEvents;

	@Inject
	private NavigationService navigationService;
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

	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public IsEventService getEventSearchService() {
		return eventService;
	}

	public void setEventSearchService(IsEventService eventService) {
		this.eventService = eventService;
	}
}
