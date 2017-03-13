package de.eventon.validator.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ("userFirstnameValidator")
public class UserFirstnameValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value.toString().equals(""))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vorname nicht gefüllt.", "Der Vorname ist nicht gefüllt.");
			throw new ValidatorException(msg);
		}
	}

}
