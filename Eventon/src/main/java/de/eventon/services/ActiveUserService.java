package de.eventon.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.User;
import de.eventon.services.interfaces.IsUserService;

@Named("activeUserService")
@SessionScoped
/**
 * Dieser Service verwaltet den User aktiven User für die derzeitige Session.
 * Hierüber kann sich ein Nutzer ein- und ausloggen.
 * 
 * @author Leon Stapper
 */
public class ActiveUserService implements Serializable {

	private static final long serialVersionUID = 5883775157529075980L;

	private User activeUser;
	@Inject
	private IsUserService userService;

	public boolean login(User user, String hashedPassword) {
		if (user != null) {
			if (user.validatePassword(hashedPassword)) {
				activeUser = user;
				return true;
			}
		}
		return false;
	}

	public void logout() {
		activeUser = null;
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

	public IsUserService getUserService() {
		return userService;
	}

	public void setUserService(IsUserService userService) {
		this.userService = userService;
	}
}