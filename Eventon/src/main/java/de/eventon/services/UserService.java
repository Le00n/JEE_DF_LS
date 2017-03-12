package de.eventon.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;

@ApplicationScoped
@ManagedBean
public class UserService {

	private List<User> users;

	public UserService() {
		users = new ArrayList<User>();
		init();
	}

	private void init() {
		User user = new User("leonstapper@gmx.de", "1234", "Leon", "Stapper", new Address("Buchdahlstraﬂe", "6", "48429", "Rheine"), new BankAccount("Leon Stapper", "DE83403500050000123456", "WELADED1RHN"));
		user.setId(1);
		addUser(user);
		
		User user2 = new User("david.feldhoff@web.de", "1234", "David", "Feldhoff", new Address("Moorstraﬂe", "88a", "48432", "Rheine"), new BankAccount("David Feldhoff", "DE83403500050000123456", "WELADED1RHN"));
		user2.setId(2);
		addUser(user2);
	}

	public Optional<User> getUserByEmail(String email) {
		return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
	}
	
	public Optional<User> getUserById(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst();
	}

	public boolean addUser(User user) {
		if (getUserByEmail(user.getEmail()).isPresent() == false) {
			int neueUserId = 1;
			if(users.size() != 0)
				neueUserId = users.stream().max((User u1, User u2) -> Integer.compare(u1.getId(), u2.getId())).get().getId() +1;
			 
			
			user.setId(neueUserId);
			
			return users.add(user);
		}
		return false;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
