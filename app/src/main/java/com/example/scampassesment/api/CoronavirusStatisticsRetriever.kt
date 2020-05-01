package com.example.scampassesment.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        //4
        service = retrofit.create(CovidService::class.java)
    }


}