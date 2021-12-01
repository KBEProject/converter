package com.kbe.service.converter.model.api;

import java.util.Date;

public class Ticker {

    private class Data {
        private String date;
        private double buy;
        private double high;
        private double last;
        private double low;
        private double sell;
        private double vol;

        public void setLast(double last) {
            this.last = last;
        }

        public double getLast() {
            return last;
        }
    }

    private Data data = new Data();
    public double getLast(){
        return data.getLast();
    }
}
