package com.kbe.service.converter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DecimalFormat;


@Document("conversions")
public class Conversion {

    @Id
    private String id;

    /**
     * user who made that conversion
     */
    private String user;

    /**
     * conversion request date
     */
    private String date;

    private String currencyName;
    private double usdvalue;
    private double coinvalue;

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getUser() {
        return user;
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
                "userId='" + user + '\'' +
                ", date='" + date + '\'' +
                ", name='" + currencyName + '\'' +
                ", usdvalue='" + usdvalue + '\'' +
                ", coinvalue='" + coinvalue + '\'' +
                '}';
    }
}
