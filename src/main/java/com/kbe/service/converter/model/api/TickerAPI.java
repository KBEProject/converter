package com.kbe.service.converter.model.api;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class TickerAPI {

    private static final String URL = "https://api.bitforex.com/api/v1/market/ticker";
    private final OkHttpClient client = new OkHttpClient();

    public Double getCurrentValue(String coinName) {
        Request request = new Request.Builder()
                .url(URL + "?symbol=" + "coin-usdt-"+coinName)
                .get()
                .build();

        Call call = client.newCall(request);

        /**Gson**/
        Gson gson = new Gson();

        try (Response response = call.execute()) {
            Ticker ticker = gson.fromJson(response.body().string(), Ticker.class);
            return ticker.getLast();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
