package com.example.scampassesment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoronavirusStatisticsRetriever {
    val service: CovidService


        //1
        const val BASE_URL = "https://api.covid19api.com/"


    init {
        // 2
        val retrofit = Retrofit.Builder()
            // 1
            .baseUrl(BASE_URL)
            //3
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //4
        service = retrofit.create(CovidService::class.java)
    }


}