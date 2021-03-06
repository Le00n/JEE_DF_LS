package de.eventon.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

	@Id 
	@GeneratedValue
	@Column(nullable=false)
	private int id;
	@Column
	private String locationName;
	@Column(nullable=false)
	private String zip;
	@Column(nullable=false)
	private String city;
	@Column(nullable=false)
	private String street;
	@Column(nullable=false)
	private String streetnumber;
	
	public Address() {
	}
	
	public Address(String street, String streetnumber, String zip, String city) {
		this.street = street;
		this.streetnumber = streetnumber;
		this.zip = zip;
		this.city = city;
	}
	
	public Address(String locationName, String street, String streetnumber, String zip, String city) {
		this.locationName = locationName;
		this.street = street;
		this.streetnumber = streetnumber;
		this.zip = zip;
		this.city = city;
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
