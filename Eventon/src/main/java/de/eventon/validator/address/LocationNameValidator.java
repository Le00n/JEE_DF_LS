package de.eventon.validator.address;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("locationValidator")
public class LocationNameValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		//Ist immer gültig
		//Klasse angelegt, um langfristig flexibel zu bleiben
	}

}
