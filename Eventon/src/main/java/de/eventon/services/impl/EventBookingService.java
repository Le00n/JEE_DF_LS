package de.eventon.services.impl;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Booking;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsEventBookingService;
import de.eventon.services.interfaces.IsLoginService;
import de.eventon.session.SessionContext;

@Named("eventBookingService")
@SessionScoped
/**
 * Dieser Service dient zur Buchung von Events. Über ihn kann ein Event von
 * einem Nutzer gebucht werden.
 * 
 * @author Leon Stapper
 */
public class EventBookingService implements Serializable, IsEventBookingService {

	private static final long serialVersionUID = 2437677069545276093L;

	@Inject
	private SessionContext sessionContext;

	public EventBookingService() {
	}

	@Override
	public Optional<UUID> bookEvent(Event event, int amountTicketsNormal, int amountTicketsPremium) {
		User user = sessionContext.getActiveUser();
		if (user != null && amountTicketsNormal <= event.getAmountFreeNormalTickets()
				&& amountTicketsPremium <= event.getAmountFreePremiumTickets()) {
			Booking booking = new Booking(event, amountTicketsNormal, amountTicketsPremium);
			user.addBooking(booking);
			event.addBooking(booking);
			return Optional.of(booking.getBookingUUID());
		}
		return Optional.empty();
	}

	public SessionContext getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
}
