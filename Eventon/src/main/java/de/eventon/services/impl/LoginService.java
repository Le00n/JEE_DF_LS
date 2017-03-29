package de.eventon.services.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.User;
import de.eventon.services.interfaces.IsLoginService;
import de.eventon.session.SessionContext;

@Named("loginService")
@SessionScoped
/**
 * Dieser Service verwaltet den User aktiven User für die derzeitige Session.
 * Hierüber kann sich ein Nutzer ein- und ausloggen.
 * 
 * @author Leon Stapper
 */
public class LoginService implements Serializable, IsLoginService {

	private static final long serialVersionUID = 5883775157529075980L;

	@Inject
	private SessionContext sessionContext;

	@Override
	public boolean login(User user, String hashedPassword) throws LoginException {
		if (user != null) {
			if (user.validatePassword(hashedPassword)) {
				sessionContext.setActiveUser(user);
				return true;
			} else {
				throw new LoginException("E-Mail oder Passwort nicht korrekt",
						"Die E-Mail-Adresse oder das Passwort ist nicht korrekt.");
			}
		}
		return false;
	}

	@Override
	public void logout() {
		sessionContext.setActiveUser(null);
	}
}