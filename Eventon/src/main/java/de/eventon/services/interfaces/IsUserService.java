package de.eventon.services.interfaces;

import java.util.Optional;

import de.eventon.core.User;

public interface IsUserService {

	/**
	 * Gibt den Nutzer mit der übergebenen E-Mail-Adresse zurück, sofern dieser
	 * vorhanden ist. Ist kein Nutzer vorhanden bleibt der Optional leer.
	 * 
	 * @param email
	 *            E-Mail-Adresse des Nutzers
	 * @return Nutzer, falls vorhanden
	 */
	Optional<User> getUserByEmail(String email);

	/**
	 * Gibt den Nutzer auf Basis der übergebenen ID zurück, sofern diese
	 * vorhanden ist. Ist kein Nutzer vorhanden bleibt der Optional leer.
	 * 
	 * @param id
	 *            ID des Nutzers
	 * @return Nutzer, falls vorhanden
	 */
	Optional<User> getUserById(int id);

	/**
	 * Fügt einen Nutzer hinzu
	 * 
	 * @param user
	 *            Nutzer
	 * @return Nutzer erfolgreich hinzugefügt?
	 */
	boolean addUser(User user);

	/**
	 * Speichert die Änderungen an einem Nutzer
	 * 
	 * @param user
	 *            Nutzer mit geänderten Werten
	 * @return Nutzer erfolgreich bearbeitet?
	 */
	boolean updateUser(User user);

}