package com.example.androidproject.Model.Market;

import com.example.androidproject.UI.Responses.MarketResponse;
import com.example.androidproject.UI.Responses.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarketApi {
    // search news

    @GET("/coins/markets")
    Call<MarketResponse> searchMarket(

            //'https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100'
            @Query("vs_currency") String currency, // usd/eur/jpy
            @Query("order") String order, // In what order it will be sorted. Default should be: market_cap_desc
            @Query("per_page")String per_page // How many cryptocurrencies are being searched for / displayed
            //@Query("price_change_percentage")String price_change_percentage // include price change percentage in 1h, 24h, 7d, 14d
            );
}
