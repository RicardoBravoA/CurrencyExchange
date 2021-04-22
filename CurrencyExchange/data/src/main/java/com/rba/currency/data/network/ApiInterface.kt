package com.rba.currency.data.network

import com.rba.currency.data.response.CountryResponse
import com.rba.currency.data.response.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("country/{origin}")
    suspend fun country(@Path("origin") origin: String): Response<CountryResponse>

    @GET("exchange/{originCurrency}/{destinationCurrency}")
    suspend fun exchange(
        @Path("originCurrency") originCurrency: String,
        @Path("destinationCurrency") destinationCurrency: String
    ): Response<ExchangeResponse>

}