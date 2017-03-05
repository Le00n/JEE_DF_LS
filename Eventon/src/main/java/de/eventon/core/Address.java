package de.eventon.core;

public class Address {

	private String plz;
	private String city;
	private String street;
	private int streetnumber;
	
	public Address(String plz, String city, String street, int streetnumber) {
		this.plz = plz;
		this.city = city;
		this.street = street;
		this.streetnumber = streetnumber;
	}
	
	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(int streetnumber) {
		this.streetnumber = streetnumber;
	}
}
