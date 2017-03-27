package de.eventon.services.interfaces;

import de.eventon.core.User;

public interface IsLoginService {

	boolean login(User user, String hashedPassword);

	void logout();

}