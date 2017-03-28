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

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#home()
	 */
	@Override
	public String home() {
		return Pages.HOME.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#searchEvents()
	 */
	@Override
	public String searchEvents() {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#login()
	 */
	@Override
	public String login() {
		return Pages.LOGIN.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#loginSuccessful()
	 */
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

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#loginFailed()
	 */
	@Override
	public String loginFailed() {
		return Pages.LOGIN.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#cancelLogin()
	 */
	@Override
	public String cancelLogin() {
		return Pages.HOME.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#logout()
	 */
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

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#register()
	 */
	@Override
	public String register() {
		return Pages.REGISTER.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#registrationSuccessful()
	 */
	@Override
	public String registrationSuccessful() {
		return Pages.LOGIN.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#registrationFailed()
	 */
	@Override
	public String registrationFailed() {
		return Pages.LOGIN.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#registrationCancelled()
	 */
	@Override
	public String registrationCancelled() {
		return Pages.HOME.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#userProfile()
	 */
	@Override
	public String userProfile() {
		return Pages.USERPROFILE.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#userDoesNotExist()
	 */
	@Override
	public String userDoesNotExist() {
		return Pages.ERROR_404.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#managerOverviewEventsReleased()
	 */
	@Override
	public String managerOverviewEventsReleased() {
		return Pages.MANAGER_OVERVIEW_EVENTS_RELEASED.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#managerOverviewEventsInProcess()
	 */
	@Override
	public String managerOverviewEventsInProcess() {
		return Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#notAuthorizedViewingManagerSites()
	 */
	@Override
	public String notAuthorizedViewingManagerSites() {
		return Pages.ERROR_404.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#eventDoesNotExist()
	 */
	@Override
	public String eventDoesNotExist() {
		return Pages.ERROR_404.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#book()
	 */
	@Override
	public String book() {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#bookWithoutLogin(int)
	 */
	@Override
	public String bookWithoutLogin(int eventId) {
		lastSignificantPage = Pages.EVENT;
		lastSignificantQuery = "?id=" + eventId;
		return login();
	}
	
	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#bookingFailed()
	 */
	@Override
	public String bookingFailed() {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#cancelBooking()
	 */
	@Override
	public String cancelBooking() {
		return Pages.HOME.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#bookingCodeSeen()
	 */
	@Override
	public String bookingCodeSeen() {
		return Pages.HOME.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#createEvent()
	 */
	@Override
	public String createEvent() {
		return Pages.CREATE_EVENT.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#createEventSuccessful(boolean)
	 */
	@Override
	public String createEventSuccessful(boolean directPublished) {
		return directPublished ? Pages.MANAGER_OVERVIEW_EVENTS_RELEASED.toString()
				: Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#createEventFailed()
	 */
	@Override
	public String createEventFailed() {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#cancelCreateEvent()
	 */
	@Override
	public String cancelCreateEvent() {
		return Pages.HOME.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#editEventSuccessful()
	 */
	@Override
	public String editEventSuccessful() {
		return Pages.MANAGER_OVERVIEW_EVENTS_IN_PROCESS.toString();
	}

	/* (non-Javadoc)
	 * @see de.eventon.services.IsNavigationService#userIsNotManager()
	 */
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
