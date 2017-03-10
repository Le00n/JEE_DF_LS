package de.eventon.ui;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.eventon.core.User;
import de.eventon.services.ActiveUserService;
import de.eventon.services.NavigationService;
import de.eventon.services.UserService;

@ManagedBean
@RequestScoped
public class UserProfileForm {

	private User user;

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{activeUserService}")
	private ActiveUserService activeUserService;

	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;

	public UserProfileForm() {
	}

	@PostConstruct
	public void init() {
		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String id = rqParameter.get("id");

		// Wurde eine gültige ID im Query-Parameter mitgegeben?
		// Ist der gesuchte Nutzer auch der eigene/der eingeloggte Nutzer?
		// Dann User anzeigen
		if (id != null) {
			try {
				int idAsInteger = Integer.parseInt(id);
				Optional<User> optUser = userService.getUserById(idAsInteger);
				if (optUser.isPresent()) {
					User queryUser = optUser.get();

					User activeUser = activeUserService.getActiveUser();
					if(activeUser != null && queryUser.equals(activeUser))
					{
						user = queryUser;
					}
				}
			} catch (NumberFormatException e) {
				user = null;
			}
		} else {
			user = null;
		}

		// Ansonsten (wenn keine gültige ID mitgegeben wurde): Redirect auf
		// ErrorPage, da der User nicht gefunden werden kann
		if (user == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.userDoesNotExist());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public NavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public ActiveUserService getActiveUserService() {
		return activeUserService;
	}

	public void setActiveUserService(ActiveUserService activeUserService) {
		this.activeUserService = activeUserService;
	}
}
