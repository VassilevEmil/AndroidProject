package com.example.androidproject.Model.Market;

import com.example.androidproject.Model.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarketServiceGenerator {

    private static MarketApi marketApi;

    public static MarketApi getMarketApi() {
        if (marketApi == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL_MARKET)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            marketApi = retrofit.create(MarketApi.class);
    }

        return marketApi;
    }
}
