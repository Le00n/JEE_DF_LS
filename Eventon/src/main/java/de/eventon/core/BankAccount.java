package de.eventon.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {
	
	@Id
	private String iban;
	@Column
	private String accountHolder;
	@Column
	private String bic;
	
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}
	
	public BankAccount(String accountHolder, String iban, String bic) {
		this.accountHolder = accountHolder;
		this.iban = iban;
		this.bic = bic;
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
}
