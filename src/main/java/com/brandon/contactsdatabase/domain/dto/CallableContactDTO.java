package com.brandon.contactsdatabase.domain.dto;

import com.brandon.contactsdatabase.domain.enumeration.PhoneType;
import com.brandon.contactsdatabase.domain.jpa.Person;
import com.brandon.contactsdatabase.domain.jpa.PersonName;
import com.brandon.contactsdatabase.domain.jpa.Phone;

import java.io.Serializable;

public class CallableContactDTO implements Serializable {
	private static final long serialVersionUID = 6472088943888554353L;

	private PersonName name;

	private String phone;

	public CallableContactDTO(Person person) {
		this.name = person.getName();
		Phone phone = person.getPhoneByType( PhoneType.HOME );
		if ( phone != null ) {
			this.phone = phone.getNumber();
		}
	}

	public PersonName getName() {
		return name;
	}

	public void setName(PersonName name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
