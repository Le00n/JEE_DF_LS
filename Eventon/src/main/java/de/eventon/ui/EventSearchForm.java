package de.eventon.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.eventon.core.Event;
import de.eventon.services.EventSearchService;
import de.eventon.services.NavigationService;

@ManagedBean
@SessionScoped
public class EventSearchForm {

	private String searchTerm;
	private List<Event> searchedEvents;

	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;
	@ManagedProperty("#{eventSearchService}")
	private EventSearchService eventSearchService;

	public EventSearchForm() {
		searchedEvents = new ArrayList<Event>();
	}

	public String searchEvents() {
		Optional<List<Event>> optSearchedEvents = eventSearchService.searchEvents(searchTerm);
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

	public EventSearchService getEventSearchService() {
		return eventSearchService;
	}

	public void setEventSearchService(EventSearchService eventSearchService) {
		this.eventSearchService = eventSearchService;
	}
}
