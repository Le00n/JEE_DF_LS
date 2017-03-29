package de.eventon.services.interfaces;

import java.util.List;
import java.util.Optional;

import de.eventon.core.Event;
import de.eventon.core.User;

public interface IsEventService {

	/**
	 * Fügt das erstellte Event zur Event-Liste hinzu.
	 * 
	 * @param event
	 *            Event
	 */
	void createEvent(Event event);

	/**
	 * Gibt ein Event anhand seiner ID zurück.
	 * 
	 * @param id
	 *            ID des Events
	 * @return Event, dessen ID der übergebenen entspricht. Ist keins vorhanden
	 *         bleibt das Optional leer.
	 */
	Optional<Event> getEventById(int id);

	/**
	 * Diese Methode regelt die Suche eines Events. Für den übergebenen
	 * Suchbegriff wird hierbei eine Liste von zutreffenden Events
	 * bereitgestellt. Als Suchkriterium gilt dabei ausschließlich der
	 * Event-Name. Groß- und Kleinschreibung ist nicht erforderlich. Außerdem
	 * werden auch Events gefunden, deren Namen lediglich Fragmente des
	 * Suchbegriffs beinhaltet. Ist kein Event für den Begriff zutreffend, ist
	 * das Optional leer.
	 * 
	 * @param searchTerm
	 *            Suchbegriff
	 * @return Liste von zutreffenden Events, falls es Treffer gibt
	 */
	Optional<List<Event>> searchEvents(String searchTerm);

	/**
	 * Updated das übergebene Event.
	 * 
	 * @param event
	 *            Event mit überarbeiteten Werten
	 */
	void updateEvent(Event event);

	/**
	 * Löscht das übergebene Event
	 * 
	 * @param event
	 *            Event, welches gelöscht werden soll
	 */
	void deleteEvent(Event event);

	/**
	 * Gibt eine Liste aller Events zu einem Manager zurück
	 * 
	 * @param manager
	 *            Manager der Events
	 * @param published
	 *            Nur die veröffentlichten oder nur die unveröffentlichten?
	 * @return Liste aller veröffentlichter/nicht veröffentlichter Events zum
	 *         Manager
	 */
	Optional<List<Event>> getManagerEvents(User manager, boolean published);

}