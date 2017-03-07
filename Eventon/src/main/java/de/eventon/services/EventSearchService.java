package de.eventon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.eventon.core.Event;

@ManagedBean
@SessionScoped //damit die Suche nicht nach Seitenwechsel verfällt
/**
 * Die Klasse EventSearchService ist verantwortlich für die Suche eines Events.
 * Sie nimmt die Suchbegriffe entgegen, verwertet sie und stellt eine Liste der
 * zutreffenenden Events bereit.
 * 
 * @author Leon Stapper
 */
public class EventSearchService {

	private String searchTerm;
	private List<Event> searchedEvents;

	@ManagedProperty("#{eventService}")
	private EventService eventService;
	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;

	public EventSearchService() {
		searchedEvents = new ArrayList<Event>();
	}

	public String searchEvents() {
		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			searchedEvents = eventService.getEvents().stream()
					.filter(event -> event.getName().toLowerCase().contains(searchTerm.trim().toLowerCase()))
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
