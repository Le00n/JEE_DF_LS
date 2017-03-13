package de.eventon.validator.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ("userEmailValidator")
public class UserEmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value.toString().equals(""))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email nicht gefüllt.", "Die Email-Adresse ist nicht gefüllt.");
			throw new ValidatorException(msg);
		}
		else
		{
			String regex = "[A-ZÄÖÜa-zäöü].*@[a-z]+\\.[a-z]+";
			if(!value.toString().matches(regex))
			{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email-Adresse ungültig.", "Die Email-Adresse ist ungültig.");
				throw new ValidatorException(msg);
			}
		}
	}

}
