package com.example.scampassesment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoronavirusStatisticsRetriever {
    val service: CovidService

    const val BASE_URL = "https://api.covid19api.com/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(CovidService::class.java)
    }


}