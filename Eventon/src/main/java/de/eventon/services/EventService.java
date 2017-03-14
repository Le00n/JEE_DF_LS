package de.eventon.services;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import de.eventon.core.Address;
import de.eventon.core.Event;
import de.eventon.core.User;

@Named("eventService")
@ApplicationScoped
/**
 * Dieser Service verwaltet alle Events. Über ihn können Events auf Basis der ID
 * oder des Namens gesucht werden sowie neue Events hinzugefügt werden.
 * 
 * @author Leon Stapper
 */
public class EventService implements Serializable {

	private static final long serialVersionUID = -5624893888361134183L;

	private List<Event> events;
	private int id;

	public EventService() {
		events = new ArrayList<Event>();
		init();
	}

	private void init() {
		User manager = new UserService().getUserByEmail("david.feldhoff@web.de").get();

		Event e = new Event();
		e.setId(0);
		e.setName("Test");
		e.setPriceTicketsNormal(40.00);
		e.setPriceTicketsPremium(60.00);
		e.setDatetime(LocalDateTime.now());
		e.setAmountTicketsNormal(5);
		e.setAmountTicketsPremium(10);
		e.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraße", "2"));
		e.setManager(manager);
		e.setPublished(true);
		createEvent(e);

		Event e1 = new Event();
		e1.setId(1);
		e1.setName("Shakespeare");
		e1.setPriceTicketsNormal(40.00);
		e1.setPriceTicketsPremium(60.00);
		e1.setDatetime(LocalDateTime.now());
		e1.setAmountTicketsNormal(5);
		e1.setAmountTicketsPremium(10);
		e1.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraße", "2"));
		e1.setManager(manager);
		e1.setPublished(true);
		createEvent(e1);

		Event e2 = new Event();
		e2.setId(2);
		e2.setName("Linkin Park Konzert");
		e2.setPriceTicketsNormal(40.00);
		e2.setPriceTicketsPremium(60.00);
		e2.setDatetime(LocalDateTime.now());
		e2.setAmountTicketsNormal(5);
		e2.setAmountTicketsPremium(10);
		e2.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraße", "2"));
		e2.setManager(manager);
		e2.setPublished(true);
		createEvent(e2);

		Event e3 = new Event();
		e3.setId(3);
		e3.setName("Kraftklub Konzert");
		e3.setPriceTicketsNormal(40.33);
		e3.setPriceTicketsPremium(60.55);
		e3.setDescription(
				"Es war eine der ganz großen Erfolgsgeschichten der letzten Jahre: Als das Kraftklub-Debüt \"Mit K\" 2012 von null auf eins in die Charts einstieg, war der vorläufige Höhepunkt einer sehr jungen und besonderen Karriere erreicht. Im Anschluss füllte die Band immer größere Hallen. Schließlich wurde \"Mit K\" mit Platin ausgezeichnet. Kein Wunder: Mit ihrem einmaligen Stilmix aus zackigen Indie-Beats, Up-Tempo-Riffs und witzig-nachdenklichen Texten, in denen sich eine ganze Generation wiederfindet, sprechen Kraftklub genre- und generationspbergreifend Hörer an.");
		e3.setDatetime(LocalDateTime.now());
		e3.setAmountTicketsNormal(5);
		e3.setAmountTicketsPremium(10);
		e3.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
		e3.setManager(manager);
		e3.setPublished(true);
		createEvent(e3);

		Event e4 = new Event();
		e4.setId(4);
		e4.setName("Theater Shakespeare");
		e4.setPriceTicketsNormal(40.00);
		e4.setPriceTicketsPremium(60.00);
		e4.setDatetime(LocalDateTime.now());
		e4.setAmountTicketsNormal(5);
		e4.setAmountTicketsPremium(10);
		e4.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraße", "2"));
		e4.setManager(manager);
		createEvent(e4);

		Event e5 = new Event();
		e5.setId(5);
		e5.setName("Shakespeare-Theater");
		e5.setPriceTicketsNormal(40.00);
		e5.setPriceTicketsPremium(60.00);
		e5.setDatetime(LocalDateTime.now());
		e5.setAmountTicketsNormal(5);
		e5.setAmountTicketsPremium(10);
		e5.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraße", "2"));
		e5.setManager(manager);
		createEvent(e5);

		Event e6 = new Event();
		e6.setId(6);
		e6.setName("Shakespeare Musical");
		e6.setPriceTicketsNormal(40.00);
		e6.setPriceTicketsPremium(60.00);
		e6.setDatetime(LocalDateTime.now());
		e6.setAmountTicketsNormal(5);
		e6.setAmountTicketsPremium(10);
		e6.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraße", "2"));
		e6.setManager(manager);
		e6.setPublished(true);
		createEvent(e6);
	}

	/**
	 * Fügt das erstellte Event zur Event-Liste hinzu.
	 * 
	 * @param event
	 *            Event
	 */
	public void createEvent(Event event) {
		event.setId(id++);
		events.add(event);
	}

	/**
	 * Gibt ein Event anhand seiner ID zurück.
	 * 
	 * @param id
	 *            ID des Events
	 * @return Event, dessen ID der übergebenen entspricht. Ist keins vorhanden
	 *         bleibt das Optional leer.
	 */
	public Optional<Event> getEventById(int id) {
		return events.stream().filter(event -> event.getId() == id).findFirst();
	}

	/**
	 * Diese Methode regelt die Suche eines Events. Für den übergebenen
	 * Suchbegriff wird hierbei eine Liste von zutreffenden Events
	 * bereitgestellt. Als Suchkriterium gilt dabei ausschließlich der
	 * Event-Name. Groß- und Kleinschreibung ist nicht erforderlich. Außerdem
	 * werden auch Events gefunden, deren Namen lediglich Fragmente des
	 * Suchbegriffs beinhaltet. Ist kein Event für den Begriff zutreffend, ist
	 * das Optional leer.
	 * 
	 * @param searchTerm
	 *            Suchbegriff
	 * @return Liste von zutreffenden Events, falls es Treffer gibt
	 */
	public Optional<List<Event>> searchEvents(String searchTerm) {
		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			List<Event> searchedEvents = getEvents().stream()
					.filter(event -> event.getName().toLowerCase().contains(searchTerm.trim().toLowerCase())
							&& event.isPublished())
					.collect(Collectors.toList());

			return Optional.of(searchedEvents);
		}

		return Optional.empty();
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
