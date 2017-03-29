package de.eventon.validator.event;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("priceTicketsValidator")
public class PriceTicketsValidator implements Validator{

	private static final String POSITIVE_PRICE_ERROR = "Preis muss positiv sein";
	private static final String POSTIVIE_PRICE_ERROR_DETAIL = "Es muss ein positiver Preis angegeben werden.";
	private static final String PRICE_NOT_NULL = "Preis verpflichtend";
	private static final String PRICE_NOT_NULL_DETAIL = "Es muss ein Preis angegeben werden.";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null && value instanceof Double){
			if(((Double) value).doubleValue() < 0.00){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, POSITIVE_PRICE_ERROR, POSTIVIE_PRICE_ERROR_DETAIL);
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, PRICE_NOT_NULL, PRICE_NOT_NULL_DETAIL);
			throw new ValidatorException(msg);
		}
	}

}
