package dev.carlocarlen.opencsv.model;

import com.opencsv.bean.CsvBindByName;

public class Address {

    @CsvBindByName(column = "address.zip")
    private Integer zip;
    @CsvBindByName(column = "address.city")
    private String city;
    @CsvBindByName(column = "address.street")
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
