package com.brandon.contactsdatabase.domain.jpa;

import com.brandon.contactsdatabase.domain.dto.PhoneDTO;
import com.brandon.contactsdatabase.domain.enumeration.PhoneType;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "PHONE")
public class Phone implements Serializable {

	private static final long serialVersionUID = 5788977337754503175L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ID_SEQ")
	@SequenceGenerator(name = "ADDRESS_ID_SEQ", sequenceName = "ADDRESS_ID_SEQ", allocationSize = 1)
	@Column(name = "PHONE_ID")
	private Long phoneId;

	@ManyToOne(optional = false, targetEntity = Person.class, cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID", nullable = false)
	private Person person;

	@Column(name = "NUMBER")
	private String number;

	@Column(name = "TYPE")
	private PhoneType type;

	public Phone() {
	}

	public Phone(PhoneDTO phone, Person person) {
		if ( phone == null || person == null ) {
			return;
		}
		BeanUtils.copyProperties( phone, this );
		this.person = person;
	}


	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}
}
