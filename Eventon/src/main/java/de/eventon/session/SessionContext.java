package de.eventon.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.eventon.core.User;

@Named
@SessionScoped
public class SessionContext implements Serializable{

	private static final long serialVersionUID = -311312770777940982L;

	private User activeUser;

	public SessionContext() {
	}
	
	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}
}
