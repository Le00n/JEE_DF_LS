package de.eventon.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import de.eventon.core.BankAccount;
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
	@Transactional
	public boolean addUser(User user) {
		if (getUserByEmail(user.getEmail()).isPresent() == false) {
//			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.persist(user.getAddress());
			if(entityManager.find(BankAccount.class, user.getBankAccount().getIban()) == null)
				entityManager.persist(user.getBankAccount());
//			entityManager.getTransaction().commit();

			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
//		entityManager.clear();	//Ansonsten wird mit find der User auch dem entityManagerContext gezogen. 
								//Der entityManager soll aber den letzten aus der Datenbank ziehen.
//		entityManager.getTransaction().begin();

		//Hole alten User und seine alte Banknummer. Nachher muss geprüft werden, ob diese noch verwendet wird
		//Der BankAccount kann aber nicht jetzt schon gelöscht werden, da er ja derzeit noch vom User verwendet wird
		//und somit eine Löschung gegen den Foreign-Key Constraint verstoßen würde.
		User oldUser = entityManager.find(User.class, user.getUserId());
		BankAccount oldBankAccount = entityManager.find(BankAccount.class, oldUser.getBankAccount().getIban());
		
		entityManager.merge(user);
		entityManager.merge(user.getAddress());
		
		//Falls der BankAccount schon existiert, soll er aktualisiert werden, ansonsten hinzugefügt. 
		BankAccount newBankAccount = entityManager.find(BankAccount.class, user.getBankAccount().getIban());
		if(newBankAccount == null){
			entityManager.persist(user.getBankAccount());
		} else{
			entityManager.merge(user.getBankAccount());
		}
		
		if(oldBankAccount.getIban() != user.getBankAccount().getIban())
		{
			//Prüfe, ob noch andere Nutzer die alte IBAN nutzen. Wenn nicht, muss der BankAccount gelöscht werden
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> query = cb.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root);
			query.where(cb.equal(root.get("bankAccount"), oldBankAccount));
			List<User> userList = entityManager.createQuery(query).getResultList();
			if(userList.isEmpty())
				entityManager.remove(oldBankAccount);
		}
//		entityManager.getTransaction().commit();
		return true;
	}
}
