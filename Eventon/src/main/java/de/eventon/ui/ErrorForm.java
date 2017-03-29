package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.services.interfaces.IsNavigationService;

@Named("errorForm")
@RequestScoped
public class ErrorForm implements Serializable {

	private static final long serialVersionUID = 5619065822976497663L;
	
	@Inject
	private IsNavigationService navigationService;

	public String home(){
		return navigationService.home();
	}
}
