package de.eventon.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.User;
import de.eventon.services.UserService;

@ManagedBean
public class UserConverter implements Converter{

	@Inject
	private UserService userService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return userService.getUserByEmail(value).orElse(null);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value != null && value instanceof User) ? ((User) value).getEmail() : null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
