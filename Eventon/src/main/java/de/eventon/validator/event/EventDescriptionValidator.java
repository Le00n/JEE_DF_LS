package de.eventon.validator.event;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("eventDescriptionValidator")
public class EventDescriptionValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value.equals(""))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Beschreibung nicht angegeben.", "Es wurde keine Beschreibung angegeben.");
			throw new ValidatorException(msg);
		}
	}

}
