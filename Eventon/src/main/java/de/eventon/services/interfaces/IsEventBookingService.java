package de.eventon.services.interfaces;

import java.util.Optional;
import java.util.UUID;

import de.eventon.core.Event;
import de.eventon.services.ActiveUserService;

public interface IsEventBookingService {

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
	Optional<UUID> bookEvent(Event event, int amountTicketsNormal, int amountTicketsPremium);

	ActiveUserService getActiveUserService();

	void setActiveUserService(ActiveUserService activeUserService);

}