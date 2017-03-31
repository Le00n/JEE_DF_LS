package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.services.interfaces.IsNavigationService;

@Named("needsLoginForm")
@RequestScoped
public class NeedsLoginForm implements Serializable{

	private static final long serialVersionUID = 2192055280668369064L;
	
	@Inject
	private IsNavigationService navigationService;

	public String login(){
		return navigationService.login();
	}
}
