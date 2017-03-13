package de.eventon.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.eventon.services.ActiveUserService;
import de.eventon.services.NavigationService;

@ManagedBean
@RequestScoped
public class HeaderForm {

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;
	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;

	public String home(){
		return navigationService.home();
	}
	
	public String login(){
		return navigationService.login();
	}
	
	public String logout(){
		activeUserService.logout();
		return navigationService.logout();
	}
	
	public String register(){
		return navigationService.register();
	}
	
	public String userProfile(){
		return navigationService.userProfile();
	}
	
	public String eventManagerOverview(){
		return navigationService.eventManagerOverview();
	}
	
	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}
}
