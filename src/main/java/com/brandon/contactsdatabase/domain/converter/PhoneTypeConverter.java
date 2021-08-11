package com.brandon.contactsdatabase.domain.converter;

import com.brandon.contactsdatabase.domain.enumeration.PhoneType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PhoneTypeConverter implements AttributeConverter<PhoneType, String> {

	@Override
	public String convertToDatabaseColumn(PhoneType phoneType) {
		return phoneType == null ? null : phoneType.getType();
	}

	@Override
	public PhoneType convertToEntityAttribute(String columnData) {
		return PhoneType.parse( columnData );
	}

}
