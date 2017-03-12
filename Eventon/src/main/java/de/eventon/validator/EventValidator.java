package de.eventon.validator;

public class EventValidator {

	public static boolean validateEvent(String eventname, String description, Integer amountTicketsNormal,
			Integer amountTicketsPremium, Double priceTicketsNormal, Double priceTicketsPremium) {
		return validateEventname(eventname) && validateDescription(description)
				&& validateAmountTickets(amountTicketsNormal, amountTicketsPremium)
				&& validatePrices(priceTicketsNormal, priceTicketsPremium);
	}

	public static boolean validateEventname(String eventname) {
		return eventname != null && !"".equals(eventname);
	}

	public static boolean validateDescription(String description) {
		return description != null && !"".equals(description);
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
		System.out.println((priceTicketsPremium != null) + " " + priceTicketsNormal.doubleValue() + " " + priceTicketsPremium.doubleValue());
		return priceTicketsNormal != null && priceTicketsPremium != null && priceTicketsNormal >= 0.00
				&& priceTicketsPremium >= 0.00 && (priceTicketsNormal <= priceTicketsPremium);
	}
}
