package de.eventon.validator.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateValidator")
public class DatePatternValidator implements Validator {

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof String)
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
			try {
				LocalDate.parse((String) value, formatter);
			} catch (DateTimeParseException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datumsformat: " + DATE_PATTERN, "Das Datumsformat sollte nach dem Muster " + DATE_PATTERN + " sein.");
				throw new ValidatorException(msg);
			}	
		}
	}
}
