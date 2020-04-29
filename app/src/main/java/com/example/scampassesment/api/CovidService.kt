package com.example.scampassesment.api

import com.example.scampassesment.model.Countries
import com.example.scampassesment.model.Summary
import com.example.scampassesment.model.WorldTotalCases
import retrofit2.Call
import retrofit2.http.GET

/**
 * Endpoints to make GET requests.
 */

interface CovidService {

    //Get countries
    @GET("/countries")
    fun retrieveCountryNames(): Call<List<Countries>>

    //Get world statistics
    @GET("/world/total")
    fun getWorldStatistics(): Call<WorldTotalCases>

    //Get world statistics
    @GET("/summary")
    fun getWorldSummary(): Call<List<Summary>>

}