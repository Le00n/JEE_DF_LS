package de.eventon.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ("bicValidator")
public class BicValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value.equals(""))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "BIC nicht gefüllt.", "Die BIC ist nicht gefüllt.");
			throw new ValidatorException(msg);
		}
		else
		{
			String regex = "^[A-Z]{7}\\d[A-Z]{3}";
			if(!value.toString().matches(regex))
			{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "BIC ungültig.", "Die angegebene BIC ist ungültig.");
				throw new ValidatorException(msg);
			}
		}
	}

}
