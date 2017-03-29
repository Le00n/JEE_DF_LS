package de.eventon.converter;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UUIDAttributeConverter implements AttributeConverter<UUID, String> {

	@Override
	public String convertToDatabaseColumn(UUID attribute) {
		System.out.println("TEST 1: " + ((attribute != null) ? attribute.toString() : null));
		return (attribute != null) ? attribute.toString() : null;
	}

	@Override
	public UUID convertToEntityAttribute(String dbData) {
		System.out.println("TEST 2: " + ((dbData != null) ? UUID.fromString(dbData) : null));
		return (dbData != null) ? UUID.fromString(dbData) : null;
	}

}