package de.eventon.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Booking;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsEventService;
import de.eventon.services.interfaces.IsNavigationService;
import de.eventon.session.SessionContext;

@Named("managerOverviewEventsForm")
@javax.faces.view.ViewScoped
public class ManagerOverviewEventsForm implements Serializable {

	private static final long serialVersionUID = -936830754297682305L;

	@Inject
	private SessionContext sessionContext;
	@Inject
	private IsNavigationService navigationService;
	@Inject
	private IsEventService eventService;

	private HashMap<Event, Boolean> mapEventPublished;
	private Event event;

	public ManagerOverviewEventsForm() {
	}

	@PostConstruct
	private void init() {
		// Funktioniert nur, falls der Manager eingeloggt ist
		if (sessionContext.getActiveUser() != null && sessionContext.getActiveUser().isManager()) {

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
	}

	public List<Event> getPublishedEvents() {
		User manager = sessionContext.getActiveUser();
		Optional<List<Event>> events = eventService.getManagerEvents(manager, true);
		if (!events.isPresent())
			return new ArrayList<Event>();
		else
		{
			System.out.println("Eventsizegröße: " + events.get().size());
			return events.get();
		}
	}

	/**
	 * published die Events, die in der Map übergeben wurden. Durch das
	 * Map-Konstrukt können zeitgleich mehrere Events gepublished werden.
	 */
	public String publishEvents() {
		System.out.println(getMapEventPublished().size());
		for (Entry<Event, Boolean> map : getMapEventPublished().entrySet()) {
			System.out.println(map.getKey().getName());
			if (map.getValue()) {
				System.out.println(map.getKey().getName() + " ist published");
				map.getKey().setPublished(true);
				eventService.updateEvent(map.getKey());
			}
		}
		System.out.println(mapEventPublished.size());
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

	/**
	 * Lädt die unpublishedEvents und fügt sie einer Map hinzu. Somit ändert die
	 * xhtml-Seite den Value des Key-Value Pairs und greift nicht direkt auf die
	 * event-Eigenschaft zu.
	 */
	public HashMap<Event, Boolean> getMapEventPublished() {
		return mapEventPublished;
	}
	public HashMap<Event, Boolean> getUnpublishedEvents(){
		if(mapEventPublished != null)
			return mapEventPublished;
		User manager = sessionContext.getActiveUser();
		Optional<List<Event>> events = eventService.getManagerEvents(manager, false);
		mapEventPublished = new HashMap<>();
		System.out.println("Bin im mapEventPublished");
		if (events.isPresent()) {
			List<Event> sortedEventList = events.get();
			sortedEventList.sort(new Comparator<Event>() {

				@Override
				public int compare(Event o1, Event o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (Event event : sortedEventList) {
				System.out.println("put " + event.getName());
				mapEventPublished.put(event, event.isPublished());
			}
		}
		return mapEventPublished;
	}

	public List<Booking> getReservations(Event event) {
		return event.getBookings();
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
