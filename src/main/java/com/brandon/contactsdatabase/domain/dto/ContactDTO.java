package com.brandon.contactsdatabase.domain.dto;

import com.brandon.contactsdatabase.domain.enumeration.PhoneType;
import com.brandon.contactsdatabase.domain.jpa.Person;
import com.brandon.contactsdatabase.domain.jpa.PersonName;
import com.brandon.contactsdatabase.domain.jpa.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ContactDTO implements Serializable {

	private static final long serialVersionUID = -2197793136265929115L;


	private Long id;

	private PersonName name;

	private AddressDTO address;

	private List<PhoneDTO> phone;

	private String email;


	public ContactDTO() {
	}

	public ContactDTO(Person person) {
		if ( person == null ) {
			return;
		}

		this.id = person.getPersonId();
		this.name = person.getName();
		this.address = new AddressDTO( person.getAddress() );

		this.phone = person.getPhones().stream()
				.map( PhoneDTO::new )
				.collect( Collectors.toList() );

		this.email = person.getEmail();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonName getName() {
		return name;
	}

	public void PersonName(PersonName name) {
		this.name = name;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public List<PhoneDTO> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneDTO> phone) {
		this.phone = phone;
	}

	@JsonIgnore
	public PhoneDTO getPhoneByType(PhoneType phoneType) {
		return this.phone.stream()
				.filter( phone -> phone.getType() == phoneType )
				.findFirst().orElse( null );
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
