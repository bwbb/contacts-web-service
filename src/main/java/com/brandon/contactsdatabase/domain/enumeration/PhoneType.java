package com.brandon.contactsdatabase.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Set;

public enum PhoneType {

	HOME("home"),
	WORK("work"),
	MOBILE("mobile");

	private static Set<PhoneType> VALUES = Set.of( PhoneType.values() );

	private String type;

	PhoneType(String typeCode) {
		this.type = typeCode;
	}

	@JsonCreator
	public static PhoneType parse(String type) {
		return VALUES.stream()
			.filter( value -> value.type.equals( type ) )
			.findFirst().orElse( null );
	}

	@JsonValue
	public String getType() {
		return type;
	}
}
