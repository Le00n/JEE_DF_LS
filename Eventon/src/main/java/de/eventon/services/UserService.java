package de.eventon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.eventon.core.Address;
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
		User user = new User("leonstapper@gmx.de", "1234", "Leon", "Stapper", new Address("48429", "Rheine", "Buchdahlstraﬂe", 6));
		user.setId(1);
		addUser(user);
	}

	public Optional<User> getUserByEmail(String email) {
		return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
	}
	
	public Optional<User> getUserById(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst();
	}

	public boolean addUser(User user) {
		if (getUserByEmail(user.getEmail()).isPresent() == false) {
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
