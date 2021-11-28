package com.kbe.service.converter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("conversion")
public class Conversion {

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

    private String usd;
    private String coin;

    public void setId(String id) {
        this.id = id;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getId() {
        return id;
    }

    public String getNonce() {
        return nonce;
    }

    public String getUsd() {
        return usd;
    }

    public String getCoin() {
        return coin;
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "id='" + id + '\'' +
                ", nonce='" + nonce + '\'' +
                ", usd='" + usd + '\'' +
                ", coin='" + coin + '\'' +
                '}';
    }
}
