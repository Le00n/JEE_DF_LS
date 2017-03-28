package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.User;
import de.eventon.services.interfaces.IsLoginService;
import de.eventon.services.interfaces.IsLoginService.LoginException;
import de.eventon.services.interfaces.IsNavigationService;

@Named("loginForm")
@RequestScoped
public class LoginForm implements Serializable{

	private static final long serialVersionUID = -5179129049568482482L;
	
	private User user;
	private String password;

	@Inject
	private IsLoginService loginService;
	@Inject
	private IsNavigationService navigationService;

	public String login() {
		try {
			if (loginService.login(user, password)) {
				return navigationService.loginSuccessful();
			} 
		} catch (LoginException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getDetailMessage());
			FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);
		}
		
		return navigationService.loginFailed();
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

	public IsLoginService getActiveUserService() {
		return loginService;
	}

	public void setActiveUserService(IsLoginService activeUserService) {
		this.loginService = activeUserService;
	}

	public IsNavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(IsNavigationService navigationService) {
		this.navigationService = navigationService;
	}
}
