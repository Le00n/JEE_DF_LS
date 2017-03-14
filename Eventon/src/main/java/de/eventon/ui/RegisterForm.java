package de.eventon.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;
import de.eventon.services.NavigationService;
import de.eventon.services.UserService;

@Named("registerForm")
@RequestScoped
public class RegisterForm implements Serializable{

	private static final long serialVersionUID = 3801859673806863588L;
	
	@Inject
	private UserService userService;
	@Inject
	private NavigationService navigationService;
	
	private String email, firstname, lastname, street, streetnumber, zip, city, password, passwordConfirm, accountHolder, iban, bic;
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
	
	public String register(){
		if(email != "" && password != "" && passwordConfirm != "" && firstname != "" && lastname != "" && zip != "" && city != "" && street != "" && streetnumber != "" && accountHolder != "" && iban != "" && bic != "")
		{
			if(password.equals(passwordConfirm) && email.contains("@"))
			{
				User user = new User(email, password, firstname, lastname, new Address(zip, city, street, streetnumber), new BankAccount(accountHolder, iban, bic), manager);
				if(userService.addUser(user))
				{
					System.out.println("User.isManager: " + user.isManager());
					return navigationService.registrationSuccessful();
				}
			}
		}
		return navigationService.registrationFailed();
	}
	public String cancel(){
		return navigationService.registrationCancelled();
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public NavigationService getNavigationService() {
		return navigationService;
	}
	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}
}
