package de.eventon.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime entityAttribute) {
		return (entityAttribute == null ? null : Timestamp.valueOf(entityAttribute));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp databaseColumn) {
		return (databaseColumn == null ? null : databaseColumn.toLocalDateTime());
	}
}
