package dev.carlocarlen.supercsv;

import dev.carlocarlen.supercsv.model.Person;
import dev.carlocarlen.supercsv.model.PersonFactory;
import dev.carlocarlen.supercsv.service.CsvColumn;
import dev.carlocarlen.supercsv.service.CsvColumnWriterWithDozer;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.io.dozer.CsvDozerBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainWithSuperCsv {

    public static void main(String[] args) {
        List<Person> personsToPrint = getPersons();
        List<CsvColumn> csvColumnsConfiguration = personCsvColumns();

        try (Writer fileWriter = new FileWriter("personsToPrint.csv");
             CsvDozerBeanWriter beanWriter = new CsvDozerBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);
             CsvColumnWriterWithDozer writerWrapper = new CsvColumnWriterWithDozer(csvColumnsConfiguration, beanWriter, Person.class)) {

            writerWrapper.writeHeaders();
            for (Person person : personsToPrint) {
                writerWrapper.writeBean(person);
            }

        } catch (IOException e) {
            handleException(e);
        }
    }

    private static void handleException(IOException e) {
        throw new RuntimeException(e);
    }

    private static List<CsvColumn> personCsvColumns() {
        return Arrays.asList(
                CsvColumn.of("firstName").withHeader("Name"),
                CsvColumn.of("lastName"),
                CsvColumn.of("birthdate").withCellProcessor(new FmtDate("yyyy-MM-dd")),
                CsvColumn.of("address.zip"),
                CsvColumn.of("address.street"),
                CsvColumn.of("address.city")
        );
    }

    private static List<Person> getPersons() {
        return List.of(
                PersonFactory.createPerson("John", "Doe", getBirthdate(1980, 5, 15), "123 Main St", 98101, "Seattle"),
                PersonFactory.createPerson("Jane", "Smith", getBirthdate(1995, 9, 20), "456 Elm Ave", 90001, "Los Angeles")
        );
    }

    private static Date getBirthdate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }
}
