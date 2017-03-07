package de.eventon.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.eventon.core.Address;
import de.eventon.core.Event;

@ApplicationScoped
@ManagedBean
public class EventService {

	private List<Event> events;

	public EventService() {
		events = new ArrayList<Event>();
		init();
	}

	private void init() {
		Event e = new Event();
		e.setName("Test");
		e.setDatetime(LocalDateTime.now());
		e.setAmountTicketsNormal(5);
		e.setAmountTicketsPremium(10);
		e.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraﬂe", 2));
		events.add(e);
		
		Event e1 = new Event();
		e1.setName("Shakespeare");
		e1.setDatetime(LocalDateTime.now());
		e1.setAmountTicketsNormal(5);
		e1.setAmountTicketsPremium(10);
		e1.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraﬂe", 2));
		events.add(e1);
		
		Event e2 = new Event();
		e2.setName("Linkin Park Konzert");
		e2.setDatetime(LocalDateTime.now());
		e2.setAmountTicketsNormal(5);
		e2.setAmountTicketsPremium(10);
		e2.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraﬂe", 2));
		events.add(e2);
		
		Event e3 = new Event();
		e3.setName("Kraftklub Konzert");
		e3.setDatetime(LocalDateTime.now());
		e3.setAmountTicketsNormal(5);
		e3.setAmountTicketsPremium(10);
		e3.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraﬂe", 2));
		events.add(e3);
		
		Event e4 = new Event();
		e4.setName("Theater Shakespeare");
		e4.setDatetime(LocalDateTime.now());
		e4.setAmountTicketsNormal(5);
		e4.setAmountTicketsPremium(10);
		e4.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraﬂe", 2));
		events.add(e4);
		
		Event e5 = new Event();
		e5.setName("Shakespeare-Theater");
		e5.setDatetime(LocalDateTime.now());
		e5.setAmountTicketsNormal(5);
		e5.setAmountTicketsPremium(10);
		e5.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraﬂe", 2));
		events.add(e5);
		
		Event e6 = new Event();
		e6.setName("Shakespeare Musical");
		e6.setDatetime(LocalDateTime.now());
		e6.setAmountTicketsNormal(5);
		e6.setAmountTicketsPremium(10);
		e6.setAddress(new Address("Stadthalle", "48429", "Rheine", "Teststraﬂe", 2));
		events.add(e6);
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
