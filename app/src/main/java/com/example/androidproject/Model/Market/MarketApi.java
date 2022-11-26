package com.example.androidproject.Model.Market;

import com.example.androidproject.Entities.Market.Market;


import java.util.List;

import retrofit2.Call;

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
}
