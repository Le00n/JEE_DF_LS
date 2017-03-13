package de.eventon.services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.eventon.core.User;

@ManagedBean
@SessionScoped
public class ActiveUserService {

	private User activeUser;
	@ManagedProperty("#{userService}")
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