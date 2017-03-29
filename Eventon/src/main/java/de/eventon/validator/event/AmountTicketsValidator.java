package de.eventon.validator.event;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("amountTicketsValidator")
public class AmountTicketsValidator implements Validator{

	private static final String POSITIVE_AMOUNT_ERROR = "Anzahl muss positiv sein";
	private static final String POSITIVE_PRICE_ERROR_DETAIL = "Es muss eine positive Anzahl angegeben werden.";
	private static final String AMOUNT_NOT_NULL = "Ticketanzahl verpflichtend";
	private static final String AMOUNT_NOT_NULL_DETAIL = "Es muss eine Ticketanzahl angegeben werden.";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null && value instanceof Integer){
			if(((Integer) value).intValue() < 0){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, POSITIVE_AMOUNT_ERROR, POSITIVE_PRICE_ERROR_DETAIL);
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, AMOUNT_NOT_NULL, AMOUNT_NOT_NULL_DETAIL);
			throw new ValidatorException(msg);
		}
	}

}
