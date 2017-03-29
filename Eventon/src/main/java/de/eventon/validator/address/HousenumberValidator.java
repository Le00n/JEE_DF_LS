package de.eventon.validator.address;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("housenumberValidator")
public class HousenumberValidator implements Validator {

	private static final String HOUSENUMBER_PATTERN = "^\\d+[a-zA-Z]?\\-?(\\d+([a-zA-Z])?)?";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof String){
			if(!((String) value).matches(HOUSENUMBER_PATTERN)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültige Hausnr.", "Dies ist keine gültige Hausnummer");
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falscher Datentyp", "Falscher Datentyp");
			throw new ValidatorException(msg);
		}
	}

}
