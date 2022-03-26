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

    public void setUsdvalue(double usdvalue) {
        this.usdvalue = usdvalue;
    }

    public void setCoinvalue(double coinvalue) {
        this.coinvalue = coinvalue;
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
