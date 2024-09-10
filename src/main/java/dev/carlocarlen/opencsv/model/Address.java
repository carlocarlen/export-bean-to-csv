package dev.carlocarlen.opencsv.model;

import com.opencsv.bean.CsvBindByName;

public class Address {
    @CsvBindByName
    private String street;
    @CsvBindByName
    private Integer zip;
    @CsvBindByName
    private String city;

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
