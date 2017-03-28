package de.eventon.services.interfaces;

public interface IsNavigationService {

	String home();

	String searchEvents();

	String login();

	String loginSuccessful();

	String loginFailed();

	String cancelLogin();

	String logout();

	String register();

	String registrationSuccessful();

	String registrationFailed();

	String registrationCancelled();

	String userProfile();

	String userDoesNotExist();

	String managerOverviewEventsReleased();

	String managerOverviewEventsInProcess();

	String notAuthorizedViewingManagerSites();

	String eventDoesNotExist();

	String book();

	String bookWithoutLogin(int eventId);

	String bookingFailed();

	String cancelBooking();

	String bookingCodeSeen();

	String createEvent();

	String createEventSuccessful(boolean directPublished);

	String createEventFailed();

	String cancelCreateEvent();

	String editEventSuccessful();

	String userIsNotManager();

}