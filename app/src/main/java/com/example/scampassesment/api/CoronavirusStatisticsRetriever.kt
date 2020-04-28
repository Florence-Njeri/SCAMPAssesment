package com.example.scampassesment.api

import com.example.scampassesment.model.Countries
import com.example.scampassesment.model.WorldTotalCases
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoronavirusStatisticsRetriever {
    private val service: CovidService

    companion object {
        //1
        const val BASE_URL = "https://api.covid19api.com/"
    }

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

    fun getCountryStatistics(callback: Callback<WorldTotalCases>) {
        //5
        val call = service.getWorldStatistics()
        call.enqueue(callback)
    }
}