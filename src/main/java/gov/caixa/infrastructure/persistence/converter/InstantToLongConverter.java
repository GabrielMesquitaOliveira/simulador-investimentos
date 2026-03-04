package gov.caixa.infrastructure.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;

/**
 * Stores Instant as epoch millis (Long) in SQLite, which doesn't have a native
 * timestamp type. Hibernate's default TimestampUtcAsJdbcTimestampJdbcType
 * fails on SQLite when reading back the numeric value as a date string.
 */
@Converter(autoApply = true)
public class InstantToLongConverter implements AttributeConverter<Instant, Long> {

    @Override
    public Long convertToDatabaseColumn(Instant instant) {
        return instant == null ? null : instant.toEpochMilli();
    }

    @Override
    public Instant convertToEntityAttribute(Long epochMilli) {
        return epochMilli == null ? null : Instant.ofEpochMilli(epochMilli);
    }
}
