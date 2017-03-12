package de.eventon.services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class NavigationService {

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;

	public NavigationService() {
	}

	public String home() {
		return Pages.HOME.toString();
	}

	public String searchEvents() {
		return Pages.STAY.toString();
	}

	public String login() {
		return Pages.LOGIN.toString();
	}

	public String loginSuccessful() {
		return Pages.HOME.toString();
	}

	public String loginFailed() {
		return Pages.LOGIN.toString();
	}

	public String cancelLogin() {
		return Pages.HOME.toString();
	}

	public String logout() {
		return Pages.STAY.toString();
	}

	public String register() {
		return Pages.REGISTER.toString();
	}
	
	public String registrationSuccessful(){
		return Pages.LOGIN.toString();
	}

	public String registrationFailed(){
		return Pages.LOGIN.toString();
	}
	
	public String userProfile() {
		return Pages.USERPROFILE.toString();
	}

	public String userDoesNotExist() {
		return Pages.ERROR_404.toString();
	}
	
	public String eventDoesNotExist() {
		return Pages.ERROR_404.toString();
	}
	
	public String book() {
		if (activeUserService.getActiveUser() == null) {
			return login();
		} else {
			return Pages.STAY.toString();
		}
	}

	public String cancelBooking() {
		return Pages.HOME.toString();
	}
	
	public String bookingCodeSeen() {
		return Pages.HOME.toString();
	}
	
	public String createEvent(){
		return Pages.CREATE_EVENT.toString();
	}
	
	public String createEventSuccessful() {
		return Pages.HOME.toString();
	}

	public String createEventFailed() {
		return Pages.STAY.toString();
	}
	
	public String cancelCreateEvent() {
		return Pages.HOME.toString();
	}
	
	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}

	public enum Pages {
		STAY("#"), LOGIN("login.jsp"), REGISTER("register.jsp"), HOME("index.jsp"), USERPROFILE("user.jsp"), BOOK(
				"book.jsp"), CREATE_EVENT("createEvent.jsp"), ERROR_404("404.jsp");

		private String value;

		private Pages(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
