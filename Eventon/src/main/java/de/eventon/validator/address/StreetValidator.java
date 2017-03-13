package de.eventon.validator.address;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("streetValidator")
public class StreetValidator implements Validator {

	private static final String STREET_PATTERN = "^[A-Z���][a-z����]+(\\.|([\\-\\ ][A-Z���][a-z����]+)*)";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof String){
			if(!((String) value).matches(STREET_PATTERN)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ung�ltiger Stra�enname", "Dies ist kein g�ltiger Stra�enname");
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falscher Datentyp", "Falscher Datentyp");
			throw new ValidatorException(msg);
		}
	}

}
