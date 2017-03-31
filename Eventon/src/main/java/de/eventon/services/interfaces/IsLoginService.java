package de.eventon.services.interfaces;

import de.eventon.core.User;

public interface IsLoginService {

	/**
	 * Meldet einen Nutzer in der Anwendung an
	 * 
	 * @param user
	 *            Anzumeldener Nutzer
	 * @param hashedPassword
	 *            Dazugehöriges Passwort
	 * @return Login erfolgreich?
	 * @throws LoginException
	 *             bei nicht erfolgreichem Login wird der Grund für den
	 *             gescheiterten Login-Versuch in der Exception abgebildet
	 */
	boolean login(User user, String hashedPassword) throws LoginException;

	/**
	 * Loggt den derzeitigen Nutzer aus der Anwendung aus
	 */
	void logout();

	/**
	 * Exception, welche bei misslungenen Logins geworfen werden kann
	 */
	public class LoginException extends Exception {

		private static final long serialVersionUID = 7233343459041474386L;

		private String detailMessage;

		public LoginException() {
		}

		public LoginException(String message, String detailMessage) {
			super(message);
			this.detailMessage = detailMessage;
		}

		public String getDetailMessage() {
			return detailMessage;
		}

	}

}