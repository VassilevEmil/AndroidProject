package com.example.androidproject.Model.Market;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.UI.Responses.MarketResponse;
import com.example.androidproject.UI.Responses.NewsResponse;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarketApi {

    @GET("api/v3/coins/markets")
    Call<List<Market>> readMarketData(
            @Query("vs_currency") String currency,
            @Query("order") String order,
            @Query("per_page") int per_page,
            @Query("price_change_percentage") String price_change_percentage
            );
    //https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false
}
