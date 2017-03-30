package de.eventon.services.impl;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsDatabaseInitializer;

@RequestScoped
public class DatabaseInitializierService implements IsDatabaseInitializer {
	@Inject
	EventService eventService;
	@Inject
	UserService userService;

	public void init(){
		System.out.println("bin drin..");
		
		User user = new User("leonstapper@gmx.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "Leon", "Stapper",
  				new Address("Buchdahlstraße", "6", "48429", "Rheine"),
  				new BankAccount("Leon Stapper", "DE83403500050000123456", "WELADED1RHN"), false);
 		user.setUserId(1);
 		userService.addUser(user);
		
		User manager = new User("david.feldhoff@web.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "David", "Feldhoff",
  				new Address("Moorstraße", "88a", "48432", "Rheine"),
  				new BankAccount("David Feldhoff", "DE83403500050000123457", "WELADED1RHN"), true);
  		manager.setUserId(2);
  		userService.addUser(manager);
		
 		Event e = new Event();
 		e.setEventId(0);
 		e.setName("OPEN R Festival 2017");
 		e.setDescription("3 Tage volles Festival-Programm!\n\nFreitag den, 4.8.17 Premiere zum ersten mal in der Geschichte des OPEN R Festivals veranstaltet die Uelzener Ferienwelt ein großartiges Programm unter dem Motto \"House & Club Music\". Das Festival präsentiert die neuen Stars am elektronischen Pophimmel Robin Schulz, Feder, Jonas Blue, Hugel und Deepend. Das Programm startet um 16 Uhr und um 15 Uhr werden die Tore des neunten Festivals geöffnet!\n\nSamstag den, 5.8.17 Uelzen OPEN R Festival \"Neue Töne\". Zum zweiten Mal steht der Samstag unter dem Motto \"Neue Töne\". Das Programm verspricht einen großartigen Sommertag in Uelzen mit den POP-Größen Silbermond, Rea Garvey, Gregoy Meyle und Max Giesinger sowie weiteren Acts. Das Programm startet um 15 Uhr und um 14 Uhr ist Einlass.\n\nSonntag den, 6.8.17 Uelzen OPEN R Festival \"Rockgeschichte\" Der Sonntag besticht durch Nachhaltigkeit und garantiert geschichtsträchtigen Rock vom Feinsten mit Roger Hodgson – formerly of Supertramp mit Band sowie Bob Geldof mit Band.\n\nSie sind Musikgeschichte, sie gehören zu den erfolgreichsten Rock-Bands weltweit. Roger Hodgson, der legendäre Gründer von Supertramp. und Bob Geldof, der irische Rocksänger ist u.a. als Sänger der Boomtown Rats bekannt sowie als der Live Aid Organisator.\n\nMehr Rockgeschichte auf einer OPEN R Bühne geht nicht. Das Programm startet um 15 Uhr und um 14 Uhr ist Einlass.");
 		e.setPriceTicketsNormal(43.33);
 		e.setPriceTicketsPremium(53.85);
 		e.setDatetime(LocalDateTime.of(2017, 8, 4, 15, 0));
 		e.setAmountTicketsNormal(15000);
 		e.setAmountTicketsPremium(1000);
 		e.setAddress(new Address("Almased Arena Uelzen", "Albrecht-Thaer-Str.", "1", "29525", "Uelzen"));
 		e.setManager(manager);
 		e.setPublished(true);
 		eventService.createEvent(e);
 
 		Event e1 = new Event();
 		e1.setEventId(1);
 		e1.setName("Shakespeare");
 		e1.setDescription("Der Wahnsinn");
 		e1.setPriceTicketsNormal(40.00);
 		e1.setPriceTicketsPremium(60.00);
 		e1.setDatetime(LocalDateTime.of(2017, 8, 7, 22, 0));
 		e1.setAmountTicketsNormal(5);
 		e1.setAmountTicketsPremium(10);
 		e1.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
 		e1.setManager(manager);
 		e1.setPublished(true);
 		eventService.createEvent(e1);
 
 		Event e2 = new Event();
 		e2.setEventId(2);
 		e2.setName("Linkin Park Konzert");
 		e2.setDescription("Der Wahnsinn");
 		e2.setPriceTicketsNormal(40.00);
 		e2.setPriceTicketsPremium(60.00);
 		e2.setDatetime(LocalDateTime.of(2017, 8, 25, 15, 0));
 		e2.setAmountTicketsNormal(5);
 		e2.setAmountTicketsPremium(10);
 		e2.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
 		e2.setManager(manager);
 		e2.setPublished(true);
 		eventService.createEvent(e2);
 
 		Event e3 = new Event();
 		e3.setEventId(3);
 		e3.setName("Kraftklub Konzert");
 		e3.setPriceTicketsNormal(40.33);
 		e3.setPriceTicketsPremium(60.55);
 		e3.setDescription(
 				"Es war eine der ganz großen Erfolgsgeschichten der letzten Jahre: Als das Kraftklub-Debüt \"Mit K\" 2012 von null auf eins in die Charts einstieg, war der vorläufige Höhepunkt einer sehr jungen und besonderen Karriere erreicht. Im Anschluss füllte die Band immer größere Hallen. Schließlich wurde \"Mit K\" mit Platin ausgezeichnet. Kein Wunder: Mit ihrem einmaligen Stilmix aus zackigen Indie-Beats, Up-Tempo-Riffs und witzig-nachdenklichen Texten, in denen sich eine ganze Generation wiederfindet, sprechen Kraftklub genre- und generationspbergreifend Hörer an.");
 		e3.setDatetime(LocalDateTime.of(2017, 6, 15, 16, 0));
 		e3.setAmountTicketsNormal(5);
 		e3.setAmountTicketsPremium(10);
 		e3.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
 		e3.setManager(manager);
 		e3.setPublished(true);
 		eventService.createEvent(e3);
 
 		Event e4 = new Event();
 		e4.setEventId(4);
 		e4.setName("Theater Shakespeare");
 		e4.setDescription("Der Wahnsinn");
 		e4.setPriceTicketsNormal(40.00);
 		e4.setPriceTicketsPremium(60.00);
 		e4.setDatetime(LocalDateTime.of(2018, 5, 4, 18, 45));
 		e4.setAmountTicketsNormal(5);
 		e4.setAmountTicketsPremium(10);
 		e4.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
 		e4.setManager(manager);
 		eventService.createEvent(e4);
 
 		Event e5 = new Event();
 		e5.setEventId(5);
 		e5.setName("Shakespeare-Theater");
 		e5.setDescription("Der Wahnsinn");
 		e5.setPriceTicketsNormal(40.00);
 		e5.setPriceTicketsPremium(60.00);
 		e5.setDatetime(LocalDateTime.of(2017, 8, 25, 19, 4));
 		e5.setAmountTicketsNormal(5);
 		e5.setAmountTicketsPremium(10);
 		e5.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
 		e5.setManager(manager);
 		eventService.createEvent(e5);
 
 		Event e6 = new Event();
 		e6.setEventId(6);
 		e6.setName("Shakespeare Musical");
 		e6.setDescription("Der Wahnsinn.");
 		e6.setPriceTicketsNormal(40.00);
 		e6.setPriceTicketsPremium(60.00);
 		e6.setDatetime(LocalDateTime.of(2017, 8, 9, 10, 0));
 		e6.setAmountTicketsNormal(5);
 		e6.setAmountTicketsPremium(10);
 		e6.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
 		e6.setManager(manager);
 		e6.setPublished(true);
 		eventService.createEvent(e6);
	}
}
