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
		if(value != null){
			value = value.replace("€", "");
			value = value.replace(",", ".");
			value = value.trim();
			
			Double d = null;
			try {
				d = new Double(value);
				return d;
			} catch (NumberFormatException e) {
				
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		DecimalFormat df = new DecimalFormat("#,###,##0.00 €");
		return df.format(value);
	}

}
