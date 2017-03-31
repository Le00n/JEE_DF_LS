package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsNavigationService;
import de.eventon.services.interfaces.IsUserService;

@Named("registerForm")
@RequestScoped
public class RegisterForm implements Serializable {

	private static final long serialVersionUID = 3801859673806863588L;

	@Inject
	private IsUserService userService;
	@Inject
	private IsNavigationService navigationService;

	private String email, firstname, lastname, street, streetnumber, zip, city, password, passwordConfirm,
			accountHolder, iban, bic;
	private boolean manager;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	/**
	 * Registriert einen Nutzer in der Anwendung
	 */
	public String register() {
		if (userService.getUserByEmail(email).isPresent()) {
			FacesContext.getCurrentInstance().addMessage("registerForm:inputEmail",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "EMail Adresse bereits registriert",
							"Die angegebene EMail Adresse ist bereits im System registriert."));
			return null;
		} else {
			if(!password.equals(passwordConfirm)){
				FacesContext.getCurrentInstance().addMessage("registerForm:inputPassword2",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwörter stimmen nicht überein","Die Passwörter stimmen nicht überein."));
				return null;
			} else {
				User user = new User(email, password, firstname, lastname, new Address(street, streetnumber, zip, city),
						new BankAccount(accountHolder, iban, bic), manager);
				if (userService.addUser(user)) {
					return navigationService.registrationSuccessful();
				}
			}
		}
		return navigationService.registrationFailed();
	}

	public String cancel() {
		return navigationService.registrationCancelled();
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
}
