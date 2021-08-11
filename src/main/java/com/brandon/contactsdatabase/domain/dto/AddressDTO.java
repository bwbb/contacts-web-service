package com.brandon.contactsdatabase.domain.dto;

import com.brandon.contactsdatabase.domain.jpa.Address;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 115822357273663816L;


	private String street;

	private String city;

	private String state;

	private String zip;

	public AddressDTO() {}

	public AddressDTO(Address address) {
		if ( address == null ) {
			return;
		}
		BeanUtils.copyProperties( address, this );
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
