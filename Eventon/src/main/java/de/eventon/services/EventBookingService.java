package de.eventon.services;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Booking;
import de.eventon.core.Event;
import de.eventon.core.User;

@Named("eventBookingService")
@SessionScoped
/**
 * Dieser Service dient zur Buchung von Events. Über ihn kann ein Event von
 * einem Nutzer gebucht werden.
 * 
 * @author Leon Stapper
 */
public class EventBookingService implements Serializable {

	private static final long serialVersionUID = 2437677069545276093L;

	@Inject
	private ActiveUserService activeUserService;

	public EventBookingService() {
	}

	/**
	 * Diese Methode bucht ein Event und gibt die ID der Buchung zurück. Die
	 * Voraussetzung hierfür ist, dass der Nutzer bereits eingeloggt ist und die
	 * angegebenene Bestellmenge die Anzahl der freien Plätze nicht
	 * überschreitet. Sind die angegebenen Voraussetzungen nicht erfüllt, wird
	 * das Event nicht gebucht. In diesem Fall wird demnach keine UUID
	 * zurückgegeben.
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
