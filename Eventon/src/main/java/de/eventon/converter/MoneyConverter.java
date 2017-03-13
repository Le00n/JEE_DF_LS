package de.eventon.converter;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("moneyConverter")
public class MoneyConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Double d = null;
		
		try {
			d = new Double(value);
		} catch (NumberFormatException e) {
			
		}
		
		return d;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		DecimalFormat df = new DecimalFormat("#,###,##0.00 €");
		return df.format(value);
	}

}
