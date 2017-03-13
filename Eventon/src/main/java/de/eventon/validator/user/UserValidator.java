package de.eventon.validator.user;

public class UserValidator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
	
	public static boolean validateEmail(String email){
		return email != null && !"".equals(email) && email.matches(EMAIL_PATTERN);
	}
}
