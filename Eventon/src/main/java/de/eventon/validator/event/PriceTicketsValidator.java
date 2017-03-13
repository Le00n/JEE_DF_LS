package de.eventon.validator.event;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("priceTicketsValidator")
public class PriceTicketsValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null && value instanceof Double){
			if(((Double) value).doubleValue() < 0.00){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preis muss positiv sein", "Es muss ein positiver Preis angegeben werden.");
				throw new ValidatorException(msg);
			}
		}
	}

}
