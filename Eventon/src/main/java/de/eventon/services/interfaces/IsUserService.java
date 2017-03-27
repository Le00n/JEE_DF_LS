package de.eventon.services.interfaces;

import java.util.List;
import java.util.Optional;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;

public interface IsUserService {

	Optional<User> getUserByEmail(String email);

	Optional<User> getUserById(int id);

	boolean addUser(User user);

	boolean updateUser(User user, String firstname, String lastname, Address address, String email,
			BankAccount bankAccount);

	List<User> getUsers();

	void setUsers(List<User> users);

}