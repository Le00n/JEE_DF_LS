package de.eventon.services;

import java.util.Optional;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.eventon.core.Booking;
import de.eventon.core.Event;
import de.eventon.core.User;

@SessionScoped
@ManagedBean
public class EventBookingService {

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;

	public EventBookingService() {
	}

	/**
	 * Diese Methode bucht ein Event und gibt die ID der Buchung zur�ck. Die
	 * Voraussetzung hierf�r ist, dass der Nutzer bereits eingeloggt ist und die
	 * angegebenene Bestellmenge die Anzahl der freien Pl�tze nicht
	 * �berschreitet. Sind die angegebenen Voraussetzungen nicht erf�llt, wird
	 * das Event nicht gebucht. In diesem Fall wird demnach keine UUID
	 * zur�ckgegeben.
	 * 
	 * @param event
	 *            Zu buchendes Event
	 * @param amountTicketsNormal
	 *            Menge der Parkett-Tickets, die gebucht werden soll
	 * @param amountTicketsPremium
	 *            Menge der Logen-Tickets, die gebucht werden soll
	 * @return ID der Buchung, sofern der Nutzer bereits eingeloggt ist
	 */
	public Optional<UUID> bookEvent(Event event, int amountTicketsNormal, int amountTicketsPremium) {
		User user = activeUserService.getActiveUser();
		if (user != null && amountTicketsNormal <= event.getAmountFreeNormalTickets()
				&& amountTicketsPremium <= event.getAmountFreePremiumTickets()) {
			Booking booking = new Booking(event, amountTicketsNormal, amountTicketsPremium);
			user.addBooking(booking);
			event.addBooking(booking);
			return Optional.of(booking.getBookingUUID());
		}
		return Optional.empty();
	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}
}
