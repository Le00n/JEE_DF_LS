package de.eventon.services.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.services.interfaces.IsNavigationService;
import de.eventon.session.SessionContext;

@Named("navigationService")
@SessionScoped
/**
 * Dieser Service dient zur Navigation innerhalb der Anwendung. Für jedes
 * mögliche Ereignis innerhalb der Anwendung ist hier eine Methode hinterlegt,
 * die beim Aufruf die Folgeseite zurückgibt.
 * 
 * @author Leon Stapper
 */
public class NavigationService implements Serializable, IsNavigationService {

	private static final long serialVersionUID = -173122607581315417L;

	@Inject
	private SessionContext sessionContext;

	private Pages lastSignificantPage;
	private String lastSignificantQuery;
	
	public NavigationService() {
	}

	@Override
	public String home() {
		return Pages.HOME.toString();
	}

	@Override
	public String searchEvents() {
		return null;
	}

	@Override
	public String login() {
		return Pages.LOGIN.toString();
	}

	@Override
	public String loginSuccessful() {
		if (lastSignificantPage == null) {
			return Pages.HOME.toString();
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(lastSignificantPage + lastSignificantQuery);
			} catch (IOException e) {
			} finally {
				lastSignificantPage = null;
				lastSignificantQuery = "";
			}
			return Pages.ERROR_404.toString();
		}
	}

	@Override
	public String loginFailed() {
		return Pages.LOGIN.toString();
	}
	
	@Override
	public String cancelLogin() {
		return Pages.HOME.toString();
	}


	@Override
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
		return null;
	}

	@Override
	public String register() {
		return Pages.REGISTER.toString();
	}

	@Override
	public String registrationSuccessful() {
		return Pages.LOGIN.toString();
	}

	@Override
	public String registrationFailed() {
		return Pages.LOGIN.toString();
	}

	@Override
	public String registrationCancelled() {
		return Pages.HOME.toString();
	}

	@Override
	public String userProfile() {
		return Pages.USERPROFILE.toString();
	}

	@Override
	public String userDoesNotExist() {
		return Pages.ERROR_404.toString();
	}

	@Override
	public String managerOverviewEventsReleased() {
		return Pages.MANAGER_OVERVIEW_EVENTS_RELEASED.toString();
	}

	@Override
	public String managerOverviewEventsInProcess() {
		return Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS.toString();
	}

	@Override
	public String notAuthorizedViewingManagerSites() {
		return Pages.ERROR_404.toString();
	}

	@Override
	public String eventDoesNotExist() {
		return Pages.ERROR_404.toString();
	}

	@Override
	public String book() {
		return null;
	}

	@Override
	public String bookWithoutLogin(int eventId) {
		lastSignificantPage = Pages.EVENT;
		lastSignificantQuery = "?id=" + eventId;
		return login();
	}

	@Override
	public String bookingFailed() {
		return null;
	}

	@Override
	public String cancelBooking() {
		return Pages.HOME.toString();
	}

	@Override
	public String bookingCodeSeen() {
		return Pages.HOME.toString();
	}

	@Override
	public String createEvent() {
		return Pages.CREATE_EVENT.toString();
	}

	@Override
	public String createEventSuccessful(boolean directPublished) {
		return directPublished ? Pages.MANAGER_OVERVIEW_EVENTS_RELEASED.toString()
				: Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS.toString();
	}

	@Override
	public String createEventFailed() {
		return null;
	}

	@Override
	public String cancelCreateEvent() {
		return Pages.HOME.toString();
	}

	@Override
	public String editEventSuccessful() {
		return Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS.toString();
	}

	@Override
	public String userIsNotManager() {
		return Pages.HOME.toString();
	}
	
	public SessionContext getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
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
		LOGIN("login.xhtml"), REGISTER("register.xhtml"), HOME("index.xhtml"), USERPROFILE(
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
