package de.eventon.validator.event;

import java.time.LocalDateTime;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class EventValidator {

	public static boolean validateDatetime(LocalDateTime dateTime, String ...id){
		LocalDateTime now = LocalDateTime.now();
		boolean datetime = now.compareTo(dateTime) < 0;
		
		if(!datetime && id.length > 0)
		{
			System.out.println("HI");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Starttermin liegt in der Vergangenheit", "Der Starttermin des Events muss in der Zukunft liegen.");
			for(String clientId : id)
			{
				FacesContext.getCurrentInstance().addMessage(clientId, msg);	
			}
		}
		
		return datetime;
	}

	/**
	 * Überprüft, ob die Anzahl der Tickets gültig ist. Keine Anzahl darf
	 * kleiner als 0 sein und mindestens eine Angabe (Loge oder Parkett) muss
	 * größer als 0 sein.
	 * 
	 * @param amountTicketsNormal
	 *            Menge der Tickets: Parkett
	 * @param amountTicketsPremium
	 *            Menge der Tickets: Loge
	 * @return true, wenn die Angabe gültig ist
	 */
	public static boolean validateAmountTickets(Integer amountTicketsNormal, Integer amountTicketsPremium) {
		return amountTicketsNormal != null && amountTicketsPremium != null && amountTicketsNormal >= 0
				&& amountTicketsPremium >= 0 && (amountTicketsNormal > 0 || amountTicketsPremium > 0);
	}

	public static boolean validatePrices(Double priceTicketsNormal, Double priceTicketsPremium) {
		return priceTicketsNormal != null && priceTicketsPremium != null && priceTicketsNormal >= 0.00
				&& priceTicketsPremium >= 0.00 && (priceTicketsNormal <= priceTicketsPremium);
	}
}
