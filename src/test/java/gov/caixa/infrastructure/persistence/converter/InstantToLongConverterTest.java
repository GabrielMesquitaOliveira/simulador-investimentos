package gov.caixa.infrastructure.persistence.converter;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InstantToLongConverterTest {

    private final InstantToLongConverter converter = new InstantToLongConverter();

    @Test
    void deveConverterInstantParaLongEpochMilli() {
        long epochMilli = 1709568000000L;
        Instant instant = Instant.ofEpochMilli(epochMilli);

        Long converted = converter.convertToDatabaseColumn(instant);

        assertEquals(epochMilli, converted);
    }

    @Test
    void convertToDatabaseColumnDeveRetornarNullQuandoInstantForNull() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void deveConverterLongEpochMilliParaInstant() {
        long epochMilli = 1709568000000L;

        Instant converted = converter.convertToEntityAttribute(epochMilli);

        assertEquals(Instant.ofEpochMilli(epochMilli), converted);
    }

    @Test
    void convertToEntityAttributeDeveRetornarNullQuandoLongForNull() {
        assertNull(converter.convertToEntityAttribute(null));
    }
}
