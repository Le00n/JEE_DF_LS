package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.services.impl.LoginService;
import de.eventon.services.interfaces.IsNavigationService;

@Named("needsLoginForm")
@RequestScoped
/**
 * Wenn der Nutzer uneingeloggt oder als Nicht-Manager auf unerwünschte Seiten
 * navigiert, kann er von dieser Seite aus zum Login navigieren bzw. sich als
 * Manager anmelden. Diese Form dient der Steuerung der beschriebenen
 * Funktionalität.
 */
public class NeedsLoginForm implements Serializable {

	private static final long serialVersionUID = 2192055280668369064L;

	@Inject
	private IsNavigationService navigationService;
	@Inject
	private LoginService loginService;

	public String login() {
		return navigationService.login();
	}

	public String loginAsManager() {
		loginService.logout();
		return navigationService.login();
	}
}
