package de.eventon.core;

public class BankAccount {
	private String accountHolder;
	private String iban;
	private String bic;
	
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
