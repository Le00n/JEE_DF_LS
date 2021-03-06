package de.eventon.services.impl;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import de.eventon.core.Booking;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsEventBookingService;
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
	@Inject
	private EntityManager entityManager;

	public EventBookingService() {
	}

	@Override
	public Optional<UUID> bookEvent(Event event, int amountTicketsNormal, int amountTicketsPremium) {
		User user = sessionContext.getActiveUser();
		if (user != null && amountTicketsNormal <= event.getAmountFreeNormalTickets()
				&& amountTicketsPremium <= event.getAmountFreePremiumTickets()) {
			Booking booking = new Booking(event, user, amountTicketsNormal, amountTicketsPremium);
			
			entityManager.getTransaction().begin();
			entityManager.persist(booking);
			entityManager.getTransaction().commit();
			entityManager.refresh(user);
			entityManager.refresh(event);
			entityManager.refresh(booking);
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
