package dev.carlocarlen.supercsv.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvColumnTest {

    @Test
    void whenNewCsvColumnCreatedHeaderValueIsTheSameAsFieldMapping() {
        CsvColumn cityColumn = CsvColumn.of("address.city");

        assertEquals("address.city", cityColumn.getHeader());
        assertEquals(cityColumn.getHeader(), cityColumn.getFieldMapping());
    }

    @Test
    void givenChainedCallsValuesAreCorrectlyUpdated() {
        CsvColumn cityColumn = CsvColumn.of("address.city")
                .withHeader("My city")
                .withHeader("Ah no, it's your city");

        assertEquals("Ah no, it's your city", cityColumn.getHeader());
    }
}