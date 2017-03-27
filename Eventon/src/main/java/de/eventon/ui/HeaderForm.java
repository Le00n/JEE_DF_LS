package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.services.NavigationService;
import de.eventon.services.interfaces.IsLoginService;

@Named("headerForm")
@RequestScoped
public class HeaderForm implements Serializable {

	private static final long serialVersionUID = 5659453191787053884L;
	
	@Inject
	private IsLoginService loginService;
	@Inject
	private NavigationService navigationService;

	public String home(){
		return navigationService.home();
	}
	
	public String login(){
		return navigationService.login();
	}
	
	public String logout(){
		loginService.logout();
		return navigationService.logout();
	}
	
	public String register(){
		return navigationService.register();
	}
	
	public String userProfile(){
		return navigationService.userProfile();
	}
	
	public String createEvent(){
		return navigationService.createEvent();
	}
	
	public String managerOverviewEventsReleased(){
		return navigationService.managerOverviewEventsReleased();
	}
	
	public String managerOverviewEventsInProcess(){
		return navigationService.managerOverviewEventsInProcess();
	}
	
	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public IsLoginService getActiveUserService() {
		return loginService;
	}

	public void setActiveUserService(IsLoginService activeUserService) {
		this.loginService = activeUserService;
	}
}
