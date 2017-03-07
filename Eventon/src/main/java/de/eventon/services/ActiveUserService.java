package de.eventon.services;

import java.util.Optional;

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
	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;

	public boolean login(String email, String hashedPassword) {
		Optional<User> userToLogin = userService.getUserByEmail(email);
		if (userToLogin.isPresent()) {
			if (hashedPassword.equals(userToLogin.get().getHashedPassword())) {
				activeUser = userToLogin.get();
				return true;
			}
		}
		return false;
	}
	
	public String logout(){
		activeUser = null;
		return navigationService.logout();
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

	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}
}