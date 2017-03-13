package de.eventon.validator.bankAccount;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ("ibanValidator")
public class IbanValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value.equals(""))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "IBAN nicht gefüllt.", "Die IBAN ist nicht gefüllt.");
			throw new ValidatorException(msg);
		}
		else
		{
			String regex = "^[A-Z]{2}\\d{20}";
			if(!value.toString().matches(regex))
			{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "IBAN ungültig.", "Die IBAN ist nicht korrekt gefüllt.");
				throw new ValidatorException(msg);
			}
		}
	}
}
