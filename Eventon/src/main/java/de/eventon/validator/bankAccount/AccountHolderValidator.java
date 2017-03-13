package de.eventon.validator.bankAccount;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class AccountHolderValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value.equals(""))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kontoinhaber nicht angegeben.", "Der Kontoinhaber ist nicht angegeben.");
			throw new ValidatorException(msg);
		}
	}

}
