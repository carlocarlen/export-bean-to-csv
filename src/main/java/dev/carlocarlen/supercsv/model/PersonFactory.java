package dev.carlocarlen.supercsv.model;

import java.util.Date;

public class PersonFactory {

    public static Person createPerson(String firstName,
                                      String lastName,
                                      Date birthdate,
                                      String street,
                                      Integer zip,
                                      String city) {
        Person person = new Person();
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setZip(zip);
        person.setAddress(address);
        person.setLastName(lastName);
        person.setFirstName(firstName);
        person.setBirthdate(birthdate);

        return person;
    }

}
