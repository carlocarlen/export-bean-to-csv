package dev.carlocarlen.supercsv.service;

import org.junit.jupiter.api.Test;
import org.supercsv.io.dozer.CsvDozerBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CsvColumnWriterWithDozerTest {

    // Using POJOS because reflection is used by Dozer, and must be compatible with writing too
    public static class Address {
        private String city;
        public Address(String city) {
            this.city = city;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
    }

    public static class Person {
        private String name;
        private Address address;

        public Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }
        public Address getAddress() {
            return address;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    @Test
    void writeHeadersPrintsHeadersAsDefinedInCsvColumns() throws IOException {
        // Given
        List<CsvColumn> csvColumns = Arrays.asList(
                CsvColumn.of("name").withHeader("Family name"),
                CsvColumn.of("address.city"));
        StringWriter stringWriter = new StringWriter();
        CsvColumnWriterWithDozer csvColumnWriter = new CsvColumnWriterWithDozer(csvColumns, new CsvDozerBeanWriter(stringWriter, CsvPreference.STANDARD_PREFERENCE), Person.class);

        // When
        csvColumnWriter.writeHeaders();
        csvColumnWriter.close();

        // Then
        String printed = stringWriter.toString();
        assertFalse(printed.isBlank());
        assertFalse(printed.lines().findFirst().isEmpty());
        String firstLine = printed.lines().findFirst().get();
        String[] split = firstLine.split(",");
        assertEquals(2, split.length);
        assertEquals("Family name", split[0].trim());
        assertEquals("address.city", split[1].trim());
    }

    @Test
    void writeBean_prints_fields_as_defined_in_csvColumns() throws IOException {
        // Given
        List<CsvColumn> csvColumns = Arrays.asList(
                CsvColumn.of("name").withHeader("Family name"),
                CsvColumn.of("address.city"));
        StringWriter stringWriter = new StringWriter();
        CsvColumnWriterWithDozer csvColumnWriter = new CsvColumnWriterWithDozer(csvColumns, new CsvDozerBeanWriter(stringWriter, CsvPreference.STANDARD_PREFERENCE), Person.class);
        Person carlo = new Person("Carlo", new Address("Lausanne"));

        // When
        csvColumnWriter.writeBean(carlo);
        csvColumnWriter.close();

        // Then
        String printed = stringWriter.toString();
        assertFalse(printed.isBlank());
        assertFalse(printed.lines().findFirst().isEmpty());
        String firstLine = printed.lines().findFirst().get();
        String[] split = firstLine.split(",");
        assertEquals(2, split.length);
        assertEquals("Carlo", split[0].trim());
        assertEquals("Lausanne", split[1].trim());
    }

}