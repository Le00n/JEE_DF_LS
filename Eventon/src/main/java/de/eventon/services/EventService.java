package de.eventon.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.eventon.core.Address;
import de.eventon.core.Event;
import de.eventon.validator.AddressValidator;
import de.eventon.validator.EventValidator;

@ApplicationScoped
@ManagedBean
public class EventService {

	private List<Event> events;
	private int id;
	
	public EventService() {
		events = new ArrayList<Event>();
		init();
	}

	private void init() {
		createEvent("Test", "Beschreibung", 5, 40.00, 10, 60.00, "Stadthalle", "Teststraße", "2a", "48429", "Rheine");
		createEvent("Shakespeare", "Beschreibung", 5, 40.00, 10, 60.00, "Stadthalle", "Teststraße", "2-8", "48429", "Rheine");
		createEvent("Linkin Park Konzert", "Beschreibung", 5, 40.00, 10, 60.00, "Stadthalle", "Teststraße", "2", "48429", "Rheine");
		createEvent("Kraftklub Konzert", "Es war eine der ganz großen Erfolgsgeschichten der letzten Jahre: Als das Kraftklub-Debüt \"Mit K\" 2012 von null auf eins in die Charts einstieg, war der vorläufige Höhepunkt einer sehr jungen und besonderen Karriere erreicht. Im Anschluss füllte die Band immer größere Hallen. Schließlich wurde \"Mit K\" mit Platin ausgezeichnet. Kein Wunder: Mit ihrem einmaligen Stilmix aus zackigen Indie-Beats, Up-Tempo-Riffs und witzig-nachdenklichen Texten, in denen sich eine ganze Generation wiederfindet, sprechen Kraftklub genre- und generationsübergreifend Hörer an.", 5, 40.00, 10, 60.00, "Stadthalle", "Teststraße", "2", "48429", "Rheine");
		createEvent("Theater Shakespeare", "Beschreibung", 5, 40.00, 10, 60.00, "Stadthalle", "Teststraße", "2", "48429", "Rheine");
		createEvent("Shakespeare-Theater", "Beschreibung", 5, 40.00, 10, 60.00, "Stadthalle", "Teststraße", "2", "48429", "Rheine");
		createEvent("Shakespeare Musical", "Beschreibung", 5, 40.00, 10, 60.00, "Stadthalle", "Teststraße", "2", "48429", "Rheine");
	}
	
	public void createEvent(String name, String description, Integer amountTicketsNormal, Double priceTicketsNormal, Integer amountTicketsPremium, Double priceTicketsPremium, String location, String street, String streetnumber, String zip, String city) {
		if(EventValidator.validateEvent(name, description, amountTicketsNormal, amountTicketsPremium, priceTicketsNormal, priceTicketsPremium))
		{
			if(AddressValidator.validateAddress(location, street, streetnumber, zip, city)){
				Address eventAddress = new Address(location, street, streetnumber, zip, city);
				Event event = new Event(name, LocalDateTime.now(), description, amountTicketsNormal.intValue(), priceTicketsNormal.doubleValue(), amountTicketsPremium.intValue(), priceTicketsPremium.doubleValue(), eventAddress);
				event.setId(id++);
				events.add(event);
			}
		}
	}
	
	public Optional<Event> getEventById(int id){
		return events.stream().filter(event -> event.getId() == id).findFirst();
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
