package com.brandon.contactsdatabase.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonName implements Serializable {
	private static final long serialVersionUID = -3514096571917560365L;

	@Column(name = "FIRST_NAME")
	private String first;

	@Column(name = "MIDDLE_NAME")
	private String middle;

	@Column(name = "LAST_NAME")
	private String last;


	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
}
