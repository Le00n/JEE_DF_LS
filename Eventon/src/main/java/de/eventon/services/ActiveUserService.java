package de.eventon.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.User;

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
	private UserService userService;

	public boolean login(User user, String hashedPassword) {
		if (user != null) {
			if (hashedPassword.equals(user.getHashedPassword())) {
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}