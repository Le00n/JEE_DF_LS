package de.eventon.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.User;
import de.eventon.services.NavigationService;
import de.eventon.services.UserService;

@ManagedBean
@RequestScoped
public class RegisterForm {

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{navigationService}")
	private NavigationService navigationService;
	
	private String email, firstname, lastname, street, streetnumber, zip, city, password, passwordConfirm, accountHolder, iban, bic;
	
	
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
	
	public String register(){
		if(email != "" && password != "" && passwordConfirm != "" && firstname != "" && lastname != "" && zip != "" && city != "" && street != "" && streetnumber != "" && accountHolder != "" && iban != "" && bic != "")
		{
			if(password.equals(passwordConfirm) && email.contains("@"))
			{
				User user = new User(email, password, firstname, lastname, new Address(zip, city, street, streetnumber), new BankAccount(accountHolder, iban, bic));
				if(userService.addUser(user))
				{
					return navigationService.registrationSuccessful();
				}
			}
		}
		return navigationService.registrationFailed();
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
