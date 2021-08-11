package com.brandon.contactsdatabase.domain.dto;

import com.brandon.contactsdatabase.domain.enumeration.PhoneType;
import com.brandon.contactsdatabase.domain.jpa.Phone;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

public class PhoneDTO implements Serializable {
	private static final long serialVersionUID = -7439818578511516505L;

	private String number;

	private PhoneType type;

	public PhoneDTO() {
	}

	public PhoneDTO(Phone phone) {
		if ( phone == null ) {
			return;
		}
		BeanUtils.copyProperties( phone, this );
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
