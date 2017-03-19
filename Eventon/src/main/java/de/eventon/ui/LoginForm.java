package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.NavigationService;

@Named("loginForm")
@RequestScoped
public class LoginForm implements Serializable{

	private static final long serialVersionUID = -5179129049568482482L;
	
	private User user;
	private String password;

	@Inject
	private ActiveUserService activeUserService;
	@Inject
	private NavigationService navigationService;

	public String login() {
		if (activeUserService.login(user, password)) {
			return navigationService.loginSuccessful();
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail oder Passwort nicht korrekt", "Die E-Mail-Adresse oder das Passwort ist nicht korrekt.");
			FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);
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
