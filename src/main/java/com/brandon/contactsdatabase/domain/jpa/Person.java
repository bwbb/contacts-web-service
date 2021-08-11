package com.brandon.contactsdatabase.domain.jpa;

import com.brandon.contactsdatabase.domain.dto.ContactDTO;
import com.brandon.contactsdatabase.domain.enumeration.PhoneType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

	private static final long serialVersionUID = 377199492483133833L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_ID_SEQ")
	@SequenceGenerator(name = "PERSON_ID_SEQ", sequenceName = "PERSON_ID_SEQ", allocationSize = 1)
	@Column(name = "PERSON_ID")
	private Long personId;

	@Embedded
	private PersonName name;

	@Column(name = "EMAIL")
	private String email;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID", nullable = false)
	private Address address;

	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH }, targetEntity = Phone.class)
	private List<Phone> phones = new ArrayList<>();

	public Person() {
	}

	public Person(ContactDTO contact) {
		this.name = contact.getName();
		this.email = contact.getEmail();
		this.address = new Address( contact.getAddress(), this );
	}


	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public PersonName getName() {
		return name;
	}

	public void setName(PersonName name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phone) {
		this.phones = phone;
	}

	public Phone getPhoneByType(PhoneType phoneType) {
		return this.phones.stream()
				.filter( phone -> phone.getType() == phoneType )
				.findFirst().orElse( null );
	}
}
