package de.eventon.services.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	@Inject
	private EntityManager entityManager;
	
	public EventService() {
	}

	@PostConstruct
	private void init() {

	}

	@Override
	public void createEvent(Event event) {
		entityManager.getTransaction().begin();
		entityManager.persist(event);
		entityManager.persist(event.getAddress());
		entityManager.getTransaction().commit();
	}
	
	@Override
	public void updateEvent(Event event){
		entityManager.getTransaction().begin();
		entityManager.merge(event);
		entityManager.merge(event.getAddress());
		entityManager.getTransaction().commit();
	}
	
	@Override
	public void deleteEvent(Event event){
		entityManager.getTransaction().begin();
		entityManager.remove(event);
		entityManager.getTransaction().commit();
	}

	@Override
	public Optional<Event> getEventById(int id) {
		Event event = entityManager.find(Event.class, id);
		return (event != null) ? Optional.of(event) : Optional.empty();
	}

	@Override
	public Optional<List<Event>> searchEvents(String searchTerm) {
		List<Event> events;
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Event> query = cb.createQuery(Event.class);
			Root<Event> root = query.from(Event.class);
			query.select(root);
			
			//Case-Insensitive-Namenssuche
			Predicate expName = cb.like(cb.lower(root.get("name")), "%" + searchTerm.toLowerCase() + "%");
			//Alle gesuchten Events müssen veröffentlicht sein
			Predicate expPublished = cb.equal(root.get("published"), true);
			//Alle gesuchten Events müssen in der Zukunft liegen
			Predicate expCurrentEvents = cb.greaterThan(root.<LocalDateTime>get("datetime"), LocalDateTime.now());
			query.where(expName, expPublished, expCurrentEvents);
			
			events = entityManager.createQuery(query).getResultList();
			return (events != null) ? Optional.of(events) : Optional.empty();
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<Event>> getManagerEvents(User manager, boolean published){
		List<Event> events;
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Event> query = cb.createQuery(Event.class);
			Root<Event> root = query.from(Event.class);
			query.select(root);
			
			//Nur die Events vom Manager anzeigen
			Predicate expManager = cb.equal(root.get("manager"), manager);
			//Veröffentlichte oder nicht veröffentlichte Events gewünscht?
			Predicate expPublished = cb.equal(root.get("published"), published);
			//Alle gesuchten Events müssen in der Zukunft liegen
			Predicate expCurrentEvents = cb.greaterThan(root.<LocalDateTime>get("datetime"), LocalDateTime.now());
			query.where(expManager, expPublished, expCurrentEvents);
			
			events = entityManager.createQuery(query).getResultList();
			return (events != null) ? Optional.of(events) : Optional.empty();
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public void publishEvent(Event event) {
		if(event != null)
		{
			event.setPublished(true);
			updateEvent(event);
		}
	}	
}
