package com.kbe.service.converter.model;

import com.kbe.service.converter.model.api.TickerAPI;
import org.springframework.stereotype.Component;

@Component
public class Converter {


    private Coin coin;
    private double coinValue;
    private static TickerAPI api = new TickerAPI();

    public Converter() {

    }

    public void setCoin(Coin coin) {
        this.coin = coin;
        this.coinValue = api.getCurrentValue(coin.getName());
    }

    public Coin getCoin() {
        return coin;
    }

    public double getCurrentCoinPrize() {
        return coinValue;
    }

    public boolean convert() {

        if (coin.getUsdvalue() > 0 && coin.getCoinvalue() == 0) {
            coin.setCoinvalue(coin.getUsdvalue() / getCurrentCoinPrize());
            return true;
        } else if (coin.getCoinvalue() > 0 && coin.getUsdvalue() == 0) {
            coin.setUsdvalue(coin.getCoinvalue() * getCurrentCoinPrize());
            System.out.println(getCurrentCoinPrize());
            System.out.println(coin.getUsdvalue());
            System.out.println(coin.getCoinvalue());
            return true;
        } else {
            return false;
        }
    }


}
