package de.eventon.services.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import de.eventon.core.Address;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsEventService;

@Named("eventService")
@ApplicationScoped
/**
 * Dieser Service verwaltet alle Events. Über ihn können Events auf Basis der ID
 * oder des Namens gesucht werden sowie neue Events hinzugefügt werden.
 * 
 * @author Leon Stapper
 */
public class EventService implements Serializable, IsEventService {

	private static final long serialVersionUID = -5624893888361134183L;

	private List<Event> events;
	private int id;

//	@Inject
//	private EntityManager entityManager;
	
	public EventService() {
		events = new ArrayList<Event>();
	}

	@PostConstruct
	private void init() {
		User manager = new UserService().getUserByEmail("david.feldhoff@web.de").get();
//		entityManager.getTransaction().begin();
//		entityManager.persist(manager);
//		entityManager.persist(manager.getAddress());
//		entityManager.persist(manager.getBankAccount());
//		entityManager.getTransaction().commit();

		Event e = new Event();
		e.setEventId(0);
		e.setName("Test");
		e.setDescription("Der Wahnsinn");
		e.setPriceTicketsNormal(40.00);
		e.setPriceTicketsPremium(60.00);
		e.setDatetime(LocalDateTime.now());
		e.setAmountTicketsNormal(5);
		e.setAmountTicketsPremium(10);
		e.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
		e.setManager(manager);
		e.setPublished(true);
		createEvent(e);

		Event e1 = new Event();
		e1.setEventId(1);
		e1.setName("Shakespeare");
		e1.setDescription("Der Wahnsinn");
		e1.setPriceTicketsNormal(40.00);
		e1.setPriceTicketsPremium(60.00);
		e1.setDatetime(LocalDateTime.now());
		e1.setAmountTicketsNormal(5);
		e1.setAmountTicketsPremium(10);
		e1.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
		e1.setManager(manager);
		e1.setPublished(true);
		createEvent(e1);

		Event e2 = new Event();
		e2.setEventId(2);
		e2.setName("Linkin Park Konzert");
		e2.setDescription("Der Wahnsinn");
		e2.setPriceTicketsNormal(40.00);
		e2.setPriceTicketsPremium(60.00);
		e2.setDatetime(LocalDateTime.now());
		e2.setAmountTicketsNormal(5);
		e2.setAmountTicketsPremium(10);
		e2.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
		e2.setManager(manager);
		e2.setPublished(true);
		createEvent(e2);

		Event e3 = new Event();
		e3.setEventId(3);
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
		e4.setEventId(4);
		e4.setName("Theater Shakespeare");
		e4.setDescription("Der Wahnsinn");
		e4.setPriceTicketsNormal(40.00);
		e4.setPriceTicketsPremium(60.00);
		e4.setDatetime(LocalDateTime.now());
		e4.setAmountTicketsNormal(5);
		e4.setAmountTicketsPremium(10);
		e4.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
		e4.setManager(manager);
		createEvent(e4);

		Event e5 = new Event();
		e5.setEventId(5);
		e5.setName("Shakespeare-Theater");
		e5.setDescription("Der Wahnsinn");
		e5.setPriceTicketsNormal(40.00);
		e5.setPriceTicketsPremium(60.00);
		e5.setDatetime(LocalDateTime.now());
		e5.setAmountTicketsNormal(5);
		e5.setAmountTicketsPremium(10);
		e5.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
		e5.setManager(manager);
		createEvent(e5);

		Event e6 = new Event();
		e6.setEventId(6);
		e6.setName("Shakespeare Musical");
		e6.setDescription("Der Wahnsinn");
		e6.setPriceTicketsNormal(40.00);
		e6.setPriceTicketsPremium(60.00);
		e6.setDatetime(LocalDateTime.now());
		e6.setAmountTicketsNormal(5);
		e6.setAmountTicketsPremium(10);
		e6.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
		e6.setManager(manager);
		e6.setPublished(true);
		createEvent(e6);
	}

	@Override
	public void createEvent(Event event) {
		event.setEventId(id++);
//		entityManager.getTransaction().begin();
//		entityManager.persist(event);
//		entityManager.persist(event.getAddress());
//		entityManager.getTransaction().commit();
	}
	
	@Override
	public void updateEvent(Event event){
//		entityManager.getTransaction().begin();
//		entityManager.merge(event);
//		entityManager.getTransaction().commit();
	}
	
	@Override
	public void deleteEvent(Event event){
//		entityManager.getTransaction().begin();
//		entityManager.remove(event);
//		entityManager.getTransaction().commit();
	}

	@Override
	public Optional<Event> getEventById(int id) {
		return events.stream().filter(event -> event.getEventId() == id).findFirst();
	}

	@Override
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

	
	@Override
	public List<Event> getEvents() {
		return events;
	}

	@Override
	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
