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
import de.eventon.services.ActiveUserService;
import de.eventon.services.interfaces.IsEventBookingService;

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
	private ActiveUserService activeUserService;

	public EventBookingService() {
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsEventBookingService#bookEvent(de.eventon.core.Event, int, int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see de.eventon.services.IsEventBookingService#getActiveUserService()
	 */
	@Override
	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsEventBookingService#setActiveUserService(de.eventon.services.ActiveUserService)
	 */
	@Override
	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}
}
