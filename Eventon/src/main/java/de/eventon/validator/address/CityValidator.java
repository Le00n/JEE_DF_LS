package de.eventon.validator.address;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cityValidator")
public class CityValidator implements Validator{

	private static final String CITY_PATTERN = "^[a-zäöüßA-ZÄÖÜ]+(?:[\\s-][a-zäöüßA-ZÄÖÜ]+)*$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof String){
			if(!((String) value).matches(CITY_PATTERN)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiger Stadtname", "Dies ist kein gültiger Stadtname");
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falscher Datentyp", "Falscher Datentyp");
			throw new ValidatorException(msg);
		}
	}
}
