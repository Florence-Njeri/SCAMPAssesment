package com.example.scampassesment.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCountry(
    @Json(name = "Country")
    val Country: String,
    @Json(name = " CountryCode")
    val CountryCode: String,
    @Json(name = "Date")
    val Date: String,
    @Json(name = "NewConfirmed")
    val NewConfirmed: Int,
    @Json(name = "NewDeaths")
    val NewDeaths: Int,
    @Json(name = "NewRecovered")
    val NewRecovered: Int,
    @Json(name = "Slug")
    val Slug: String,
    @Json(name = "TotalConfirmed")
    val TotalConfirmed: Int,
    @Json(name = "TotalDeaths")
    val TotalDeaths: Int,
    @Json(name = "TotalRecovered")
    val TotalRecovered: Int
)

