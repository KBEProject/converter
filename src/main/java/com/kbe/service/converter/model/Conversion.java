package com.kbe.service.converter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
@Document("conversions")
public class Conversion {

    @Id
    private String id;

    /**
     * user who made that conversion
     */
    private String email;

    /**
     * conversion request date
     */
    private String date;

    private String currencyName;
    private double usdvalue;
    private double coinvalue;

    public Conversion() {
    }

    public Conversion(String id, String email, String date, String currencyName, double usdvalue, double coinvalue) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.currencyName = currencyName;
        this.usdvalue = usdvalue;
        this.coinvalue = coinvalue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setUsdvalue(double usdvalue) {
        this.usdvalue = usdvalue;
    }

    public void setCoinvalue(double coinvalue) {
        this.coinvalue = coinvalue;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getUsdvalue() {
        return this.usdvalue;
    }

    public double getCoinvalue() {
        return this.coinvalue;
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "id='" + id + '\'' +
                "email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", name='" + currencyName + '\'' +
                ", usdvalue='" + usdvalue + '\'' +
                ", coinvalue='" + coinvalue + '\'' +
                '}';
    }
}
