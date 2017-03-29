package de.eventon.validator.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("timeValidator")
public class TimePatternValidator implements Validator{
	
	private static final String TIME_PATTERN = "HH:mm";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof String)
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
			try {
				LocalTime.parse((String) value, formatter);
			} catch (DateTimeParseException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zeitformat: " + TIME_PATTERN, "Das Zeitformat sollte nach dem Muster " + TIME_PATTERN + " sein.");
				throw new ValidatorException(msg);
			}	
		}
	}
}
