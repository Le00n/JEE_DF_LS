package de.eventon.core;

public class Address {

	private String locationName;
	private String zip;
	private String city;
	private String street;
	private String streetnumber;
	
	public Address(String zip, String city, String street, String streetnumber) {
		this.zip = zip;
		this.city = city;
		this.street = street;
		this.streetnumber = streetnumber;
	}
	
	public Address(String locationName, String zip, String city, String street, String streetnumber) {
		this.locationName = locationName;
		this.zip = zip;
		this.city = city;
		this.street = street;
		this.streetnumber = streetnumber;
	}
	
	public String getZIP() {
		return zip;
	}

	public void setZIP(String plz) {
		this.zip = plz;
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

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}