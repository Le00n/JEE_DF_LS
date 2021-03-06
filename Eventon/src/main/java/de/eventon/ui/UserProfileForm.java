package de.eventon.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;
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
		// Nutzer muss eingeloggt sein
		// Man kann natürlich nur sein eigenes Profil einsehen und bearbeiten
		User activeUser = sessionContext.getActiveUser();
		if (activeUser != null) {
			user = activeUser;
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

	/**
	 * Prüft, ob die Änderung der E-Mail Adresse gültig ist oder ob diese schon
	 * von einem anderen User verwendet wird.
	 */
	public String save() {
		if (getUserService().getUserByEmail(email).isPresent()) {
			boolean isError = true;
			User activeUser = getSessionContext().getActiveUser();
			if (activeUser != null) {
				if (activeUser.getUserId() == getUserService().getUserByEmail(email).get().getUserId())
					isError = false;
			}
			if (isError) {
				FacesContext.getCurrentInstance().addMessage("userForm:inputEmail",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die E-Mail-Adresse wird bereits verwendet.",
								"Die E-Mail-Adresse wird bereits verwendet."));
				return null;
			}
		}

		user.getAddress().setStreet(street);
		user.getAddress().setStreetnumber(housenumber);
		user.getAddress().setZip(zip);
		user.getAddress().setCity(city);

		user.getBankAccount().setAccountHolder(accountHolder);
		user.getBankAccount().setIban(iban);
		user.getBankAccount().setBic(bic);

		user.setFirstname(firstname);
		user.setLastname(lastname);

		user.setEmail(email);

		userService.updateUser(user);
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
