package de.eventon.services.impl;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.eventon.core.User;
import de.eventon.services.interfaces.IsUserService;

@Named("userService")
@ApplicationScoped
/**
 * Dieser Service verwaltet alle Nutzer der Anwender. Über ihn können Nutzer auf
 * Basis der ID oder der Email gesucht werden sowie neue Nutzer hinzugefügt oder
 * geupdated werden.
 * 
 * @author Leon Stapper
 */
public class UserService implements Serializable, IsUserService {

	private static final long serialVersionUID = -1460978899898153682L;
	
	@Inject
	private EntityManager entityManager;

	public UserService() {
	}

	@PostConstruct
	private void init() {
//		User user = new User("leonstapper@gmx.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "Leon", "Stapper",
//				new Address("Buchdahlstraße", "6", "48429", "Rheine"),
//				new BankAccount("Leon Stapper", "DE83403500050000123456", "WELADED1RHN"), false);
//		addUser(user);

//		User user2 = new User("david.feldhoff@web.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "David", "Feldhoff",
//				new Address("Moorstraße", "88a", "48432", "Rheine"),
//				new BankAccount("David Feldhoff", "DE83403500050000123457", "WELADED1RHN"), true);
//		addUser(user2);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		User user;
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> query = cb.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root);
			query.where(cb.equal(root.get("email"), email));
			user = entityManager.createQuery(query).getSingleResult();
			
			return (user != null) ? Optional.of(user) : Optional.empty();
		} catch (Exception e) {
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<User> getUserById(int id) {
		User user = entityManager.find(User.class, id);
		return (user != null) ? Optional.of(user) : Optional.empty();
	}

	@Override
	public boolean addUser(User user) {
		if (getUserByEmail(user.getEmail()).isPresent() == false) {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.persist(user.getAddress());
			entityManager.persist(user.getBankAccount());
			entityManager.getTransaction().commit();

			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.merge(user.getAddress());
		entityManager.merge(user.getBankAccount());
		entityManager.getTransaction().commit();
		return true;
	}
}
