package de.eventon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.eventon.core.Event;

@ManagedBean
@RequestScoped
public class EventSearchService {

	private String searchTerm;
	private List<Event> searchedEvents;

	@ManagedProperty("#{eventService}")
	private EventService eventService;
	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;

	public EventSearchService() {

	}

	public String searchEvents() {
		if (searchTerm != null) {
			searchedEvents = eventService.getEvents().stream()
					.filter(event -> event.getName().toLowerCase().contains(searchTerm.toLowerCase()))
					.collect(Collectors.toList());
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

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}
}
