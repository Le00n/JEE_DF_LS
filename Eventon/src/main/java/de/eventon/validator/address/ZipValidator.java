package de.eventon.validator.address;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("zipValidator")
public class ZipValidator implements Validator{

	private static final String ZIP_PATTERN = "^(?!01000|99999)(0[1-9]\\d{3}|[1-9]\\d{4})$";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof String){
			if(!((String) value).matches(ZIP_PATTERN)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültige PLZ", "Dies ist keine gültige Postleitzahl");
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falscher Datentyp", "Falscher Datentyp");
			throw new ValidatorException(msg);
		}
	}

}
