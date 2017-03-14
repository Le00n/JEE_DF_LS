package de.eventon.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.eventon.core.Event;

@ManagedBean
@SessionScoped //damit die Suche nicht nach Seitenwechsel verf�llt
/**
 * Die Klasse eventSearchForm ist verantwortlich f�r die Suche eines Events.
 * Sie nimmt die Suchbegriffe entgegen, verwertet sie und stellt eine Liste der
 * zutreffenenden Events bereit.
 * 
 * @author Leon Stapper
 */
public class EventSearchService {

	@ManagedProperty("#{eventService}")
	private EventService eventService;

	public EventSearchService() {
		
	}

	public Optional<List<Event>> searchEvents(String searchTerm) {
		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			List<Event> searchedEvents = eventService.getEvents().stream()
					.filter(event -> event.getName().toLowerCase().contains(searchTerm.trim().toLowerCase()) && event.isPublished())
					.collect(Collectors.toList());
			
			return Optional.of(searchedEvents);
		}
		
		return Optional.empty();
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
