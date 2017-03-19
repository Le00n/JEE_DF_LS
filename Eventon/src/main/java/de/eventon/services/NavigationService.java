package de.eventon.services;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("navigationService")
@SessionScoped
/**
 * Dieser Service dient zur Navigation innerhalb der Anwendung. Für jedes
 * mögliche Ereignis innerhalb der Anwendung ist hier eine Methode hinterlegt,
 * die beim Aufruf die Folgeseite zurückgibt.
 * 
 * @author Leon Stapper
 */
public class NavigationService implements Serializable {

	private static final long serialVersionUID = -173122607581315417L;

	@Inject
	private ActiveUserService activeUserService;

	private Pages lastSignificantPage;

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
		if (lastSignificantPage == null) {
			return Pages.HOME.toString();
		} else {
			return Pages.HOME.toString();
		}
	}

	public String loginFailed() {
		return Pages.LOGIN.toString();
	}

	public String cancelLogin() {
		return Pages.HOME.toString();
	}

	public String logout() {
		// Wenn die derzeitige Seite eine Manager-Seite ist
		// --> Navigation zu Home
		// Ansonsten auf der Seite bleiben (Stay)
		Optional<Pages> optPages = Pages.getPage(FacesContext.getCurrentInstance().getViewRoot());
		if (optPages.isPresent()) {
			if (optPages.get().equals(Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS) //
					|| optPages.get().equals(Pages.MANAGER_OVERVIEW_EVENTS_RELEASED) //
					|| optPages.get().equals(Pages.CREATE_EVENT) //
					|| optPages.get().equals(Pages.USERPROFILE)) { //
				return Pages.HOME.toString();
			}
		}
		return Pages.STAY.toString();
	}

	public String register() {
		return Pages.REGISTER.toString();
	}

	public String registrationSuccessful() {
		return Pages.LOGIN.toString();
	}

	public String registrationFailed() {
		return Pages.LOGIN.toString();
	}

	public String registrationCancelled() {
		return Pages.HOME.toString();
	}

	public String userProfile() {
		return Pages.USERPROFILE.toString();
	}

	public String userDoesNotExist() {
		return Pages.ERROR_404.toString();
	}

	public String managerOverviewEventsReleased() {
		return Pages.MANAGER_OVERVIEW_EVENTS_RELEASED.toString();
	}

	public String managerOverviewEventsInProcess() {
		return Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS.toString();
	}

	public String notAuthorizedViewingManagerSites() {
		return Pages.ERROR_404.toString();
	}

	public String eventDoesNotExist() {
		return Pages.ERROR_404.toString();
	}

	public String book() {
		if (activeUserService.getActiveUser() == null) {
			lastSignificantPage = Pages.EVENT;
			return login();
		} else {
			return Pages.STAY.toString();
		}
	}

	public String bookingFailed() {
		return null;
	}
	
	public String cancelBooking() {
		return Pages.HOME.toString();
	}

	public String bookingCodeSeen() {
		return Pages.HOME.toString();
	}

	public String createEvent() {
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

	public String userIsNotManager() {
		return Pages.HOME.toString();
	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}

	public Pages getLastSignificantPage() {
		return lastSignificantPage;
	}

	public void setLastSignificantPage(Pages lastSignificantPage) {
		this.lastSignificantPage = lastSignificantPage;
	}

	/**
	 * Enum, welches alle Seiten der Anwendung speichert.
	 * 
	 * @author Leon Stapper
	 */
	public enum Pages {
		STAY("#"), LOGIN("login.xhtml"), REGISTER("register.xhtml"), HOME("index.xhtml"), USERPROFILE(
				"user.xhtml"), MANAGER_OVERVIEW_EVENTS_RELEASED(
						"managerOverviewEventsReleased.xhtml"), MANAGER_OVERVIEW_EVENTS_IN_PROCESS(
								"managerOverviewEventsInProcess.xhtml"), EVENT(
										"event.xhtml"), CREATE_EVENT("createEvent.xhtml"), ERROR_404("404.xhtml");

		private String value;

		private Pages(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		public static Optional<Pages> getPage(UIViewRoot view) {
			String pageString = view.getViewId().replace("/", "");
			return Arrays.asList(Pages.values()).stream().filter(page -> page.toString().equals(pageString))
					.findFirst();
		}
	}
}
