package de.eventon.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.NavigationService;

@ManagedBean
@RequestScoped
public class LoginForm {

	private User user;
	private String password;

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;
	
	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;

	public String login() {
		if (activeUserService.login(user, password)) {
			return navigationService.loginSuccessful();
		} else {
			return navigationService.loginFailed();
		}
	}

	public String cancel(){
		return navigationService.cancelLogin();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}

	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}
}
