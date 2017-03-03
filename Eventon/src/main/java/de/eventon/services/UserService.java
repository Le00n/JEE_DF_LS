package de.eventon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.eventon.core.User;

public class UserService {

	private List<User> users;

	public UserService() {
		users = new ArrayList<User>();
	}

	public Optional<User> getUserByEmail(String email) {
		return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
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
