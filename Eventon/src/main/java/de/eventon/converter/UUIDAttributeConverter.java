package de.eventon.converter;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UUIDAttributeConverter implements AttributeConverter<UUID, String> {

	@Override
	public String convertToDatabaseColumn(UUID attribute) {
		return (attribute != null) ? attribute.toString() : null;
	}

	@Override
	public UUID convertToEntityAttribute(String dbData) {
		return (dbData != null) ? UUID.fromString(dbData) : null;
	}

}