package dev.carlocarlen.supercsv;

import dev.carlocarlen.supercsv.model.Person;
import dev.carlocarlen.supercsv.model.PersonFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainWithSuperCsv {

    public static void main(String[] args) {
        List<Person> persons = createPersons();

        try (Writer fileWriter = new FileWriter("persons.csv") ) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Person> createPersons() {
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
