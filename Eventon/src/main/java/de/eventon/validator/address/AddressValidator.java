package de.eventon.validator.address;

public class AddressValidator{

	private static final String HOUSENUMBER_PATTERN = "^\\d+[a-zA-Z]?\\-?(\\d+([a-zA-Z])?)?";
	
	public static boolean validateAddress(String locationname, String street, String housenumber, String zip,
			String city) {
		return validateLocationname(locationname) && validateStreet(street) && validateHousenumber(housenumber)
				&& validateZip(zip) && validateCity(city);
	}

	public static boolean validateLocationname(String locationname) {
		return true;
	}

	public static boolean validateStreet(String street) {
		return street != null && !"".equals(street);
	}

	public static boolean validateHousenumber(String housenumber) {
		return housenumber.matches(HOUSENUMBER_PATTERN);
	}

	public static boolean validateZip(String zip) {
		return zip != null && !"".equals(zip);
	}

	public static boolean validateCity(String city) {
		return city != null && !"".equals(city);
	}
}
