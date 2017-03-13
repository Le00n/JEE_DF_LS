package de.eventon.validator.event;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("amountTicketsValidator")
public class AmountTicketsValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null && value instanceof Integer){
			if(((Integer) value).intValue() < 0){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Anzahl muss positiv sein", "Es muss eine positive Anzahl angegeben werden.");
				throw new ValidatorException(msg);
			}
		}
	}

}
