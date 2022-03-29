package com.kbe.service.converter.model;

import com.kbe.service.converter.model.api.TickerAPI;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

@Component
public class Converter {


    private Conversion conversion;
    private double currencyValue;
    private static TickerAPI api = new TickerAPI();

    public Converter() {

    }

    public Converter(Conversion conversion) {
        this.conversion = conversion;
    }

    public void setConversion(Conversion conversion) {
        this.conversion = conversion;
    }

    public void setCurrencyValueFromAPI(){
        this.currencyValue = api.getCurrentValue(conversion.getCurrencyName());
    }

    public void setOwnCurrencyValue(double currencyValue){
        this.currencyValue = currencyValue;
    }

    public Conversion getConversion() {
        return conversion;
    }

    public double getCurrentCurrencyPrize() {
        return currencyValue;
    }

    public boolean convert() {

        if (conversion.getUsdvalue() > 0 && conversion.getCoinvalue() == 0) {
            NumberFormat formatter = new DecimalFormat("0.00000", DecimalFormatSymbols.getInstance(Locale.US));
            conversion.setCoinvalue(Double.parseDouble(formatter.format(conversion.getUsdvalue() / getCurrentCurrencyPrize())));
            return true;
        } else if (conversion.getCoinvalue() > 0 && conversion.getUsdvalue() == 0) {
            conversion.setUsdvalue(conversion.getCoinvalue() * getCurrentCurrencyPrize());
            return true;
        } else {
            return false;
        }
    }


}
