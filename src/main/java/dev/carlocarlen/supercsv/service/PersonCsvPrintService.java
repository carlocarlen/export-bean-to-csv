package dev.carlocarlen.supercsv.service;

import dev.carlocarlen.supercsv.model.Person;
import org.supercsv.io.dozer.CsvDozerBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class PersonCsvPrintService {

    private final List<CsvColumn> csvColumnList;

    public PersonCsvPrintService(List<CsvColumn> csvColumnList) {
        this.csvColumnList = csvColumnList;
    }

    public void print(List<Person> personList, Writer writer) throws IOException {
        CsvColumnWriterWithDozer writerWithDozer = new CsvColumnWriterWithDozer(csvColumnList, new CsvDozerBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE), Person.class);
        writerWithDozer.writeHeaders();
        for (Person person : personList) {
            writerWithDozer.writeBean(person);
        }
        writerWithDozer.close();
    }
}
