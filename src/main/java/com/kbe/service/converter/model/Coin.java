package com.kbe.service.converter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("conversion")
public class Coin {

    @Id
    private String id;

    /**
     * user who made that conversion
     */
    private String userId;

    /**
     * Time in milliseconds
     */
    private String nonce;

    private String name;
    private double usdvalue;
    private double coinvalue;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserId() {
        return userId;
    }

    public String getNonce() {
        return nonce;
    }

    public String getName() {
        return name;
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
                "userId='" + userId + '\'' +
                ", nonce='" + nonce + '\'' +
                ", name='" + name + '\'' +
                ", usdvalue='" + usdvalue + '\'' +
                ", coinvalue='" + coinvalue + '\'' +
                '}';
    }
}
