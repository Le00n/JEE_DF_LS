package de.eventon.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;
import de.eventon.services.impl.LoginService;
import de.eventon.services.interfaces.IsLoginService;
import de.eventon.services.interfaces.IsNavigationService;
import de.eventon.services.interfaces.IsUserService;
import de.eventon.session.SessionContext;

@Named("userProfileForm")
@ViewScoped
public class UserProfileForm implements Serializable {

	private static final long serialVersionUID = 6985231480343494936L;

	private User user;
	private String firstname, lastname, street, housenumber, zip, city, email, accountHolder, iban, bic;

	@Inject
	private IsUserService userService;
	@Inject
	private SessionContext sessionContext;
	@Inject
	private IsNavigationService navigationService;

	public UserProfileForm() {

	}

	@PostConstruct
	public void init() {
		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String id = rqParameter.get("id");

		// Wurde eine gültige ID im Query-Parameter mitgegeben?
		// Ist der gesuchte Nutzer auch der eigene/der eingeloggte Nutzer?
		// Dann User anzeigen
		if (id != null) {
			try {
				int idAsInteger = Integer.parseInt(id);
				Optional<User> optUser = userService.getUserById(idAsInteger);
				if (optUser.isPresent()) {
					User queryUser = optUser.get();

					User activeUser = sessionContext.getActiveUser();
					if (activeUser != null && queryUser.equals(activeUser)) {
						user = queryUser;
						firstname = user.getFirstname();
						lastname = user.getLastname();
						email = user.getEmail();

						Address address = user.getAddress();
						street = address.getStreet();
						housenumber = address.getStreetnumber();
						zip = address.getZip();
						city = address.getCity();

						BankAccount bankAccount = user.getBankAccount();
						accountHolder = bankAccount.getAccountHolder();
						iban = bankAccount.getIban();
						bic = bankAccount.getBic();
					}
				}
			} catch (NumberFormatException e) {
				user = null;
			}
		} else {
			user = null;
		}

		// Ansonsten (wenn keine gültige ID mitgegeben wurde): Redirect auf
		// ErrorPage, da der User nicht gefunden werden kann
		if (user == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigationService.userDoesNotExist());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String save() {
		Address address = new Address(street, housenumber, zip, city);
		BankAccount bankAccount = new BankAccount(accountHolder, iban, bic);
		userService.updateUser(user, firstname, lastname, address, email, bankAccount);
		return navigationService.home();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public IsUserService getUserService() {
		return userService;
	}

	public void setUserService(IsUserService userService) {
		this.userService = userService;
	}

	public IsNavigationService getNavigationService() {
		return navigationService;
	}

	public void setNavigationService(IsNavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public SessionContext getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
}
