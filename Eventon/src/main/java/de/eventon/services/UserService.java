package de.eventon.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;

@Named("userService")
@ApplicationScoped
/**
 * Dieser Service verwaltet alle Nutzer der Anwender. Über ihn können Nutzer auf
 * Basis der ID oder der Email gesucht werden sowie neue Nutzer hinzugefügt oder
 * geupdated werden.
 * 
 * @author Leon Stapper
 */
public class UserService implements Serializable {

	private static final long serialVersionUID = -1460978899898153682L;

	private List<User> users;

	public UserService() {
		users = new ArrayList<User>();
		init();
	}

	private void init() {
		User user = new User("leonstapper@gmx.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "Leon", "Stapper",
				new Address("Buchdahlstraße", "6", "48429", "Rheine"),
				new BankAccount("Leon Stapper", "DE83403500050000123456", "WELADED1RHN"), false);
		user.setUserId(1);
		addUser(user);

		User user2 = new User("david.feldhoff@web.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "David", "Feldhoff",
				new Address("Moorstraße", "88a", "48432", "Rheine"),
				new BankAccount("David Feldhoff", "DE83403500050000123456", "WELADED1RHN"), true);
		user2.setUserId(2);
		addUser(user2);
	}

	public Optional<User> getUserByEmail(String email) {
		return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
	}

	public Optional<User> getUserById(int id) {
		return users.stream().filter(user -> user.getUserId() == id).findFirst();
	}

	public boolean addUser(User user) {
		if (getUserByEmail(user.getEmail()).isPresent() == false) {
			int neueUserId = 1;
			if (users.size() != 0)
				neueUserId = users.stream().max((User u1, User u2) -> Integer.compare(u1.getUserId(), u2.getUserId())).get()
						.getUserId() + 1;

			user.setUserId(neueUserId);

			return users.add(user);
		}
		return false;
	}

	public boolean updateUser(User user, String firstname, String lastname, Address address, String email,
			BankAccount bankAccount) {
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setAddress(address);
		user.setBankAccount(bankAccount);
		return true;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
