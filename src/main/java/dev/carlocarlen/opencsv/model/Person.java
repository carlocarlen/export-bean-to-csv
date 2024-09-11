package dev.carlocarlen.opencsv.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;

import java.util.Date;

public class Person {

    @CsvBindByName(column = "firstName")
    private String firstName;
    @CsvBindByName(column = "lastName")
    private String lastName;
    @CsvDate("yyyy-MM-dd")
    @CsvBindByName(column = "birthdate")
    private Date birthdate;
    @CsvRecurse
    private Address address;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
