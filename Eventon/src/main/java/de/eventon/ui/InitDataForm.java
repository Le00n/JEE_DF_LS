package de.eventon.ui;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.services.impl.DatabaseInitializierService;
import de.eventon.services.impl.NavigationService;

@RequestScoped
@Named("initDataForm")
/**
 * Dient zur Initialisierung von Testdaten
 */
public class InitDataForm {
	@Inject
	DatabaseInitializierService dbInitService;
	@Inject
	NavigationService navigationService;
	
	public void init(){
		dbInitService.init();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.home());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
