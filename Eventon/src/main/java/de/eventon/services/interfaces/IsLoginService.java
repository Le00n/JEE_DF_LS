package de.eventon.services.interfaces;

import de.eventon.core.User;

public interface IsLoginService {

	boolean login(User user, String hashedPassword) throws LoginException;

	void logout();
	
	public class LoginException extends Exception{

		private static final long serialVersionUID = 7233343459041474386L;

		private String detailMessage;
		
		public LoginException() {
			// TODO Auto-generated constructor stub
		}
		
		public LoginException(String message, String detailMessage){
			super(message);
			this.detailMessage = detailMessage;
		}
		
		public String getDetailMessage() {
			return detailMessage;
		}
		
	}

}