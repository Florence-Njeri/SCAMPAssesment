package com.example.scampassesment.model

data class WorldTotalCases(
    val TotalConfirmed:Int,
    val TotalDeaths:Int,
    val TotalRecovered:Int
)

/**
 * input{ baseUrl, country}
 */
data class CountryStatistics(
    val Country:String,
    val Confirmed:Int,
    val Recovered:Int,
    val Deaths:Int,
    val Active:Int

)

data class Countries(
    val Country:String
)

/**
 * https://api.covid19api.com/summary
 */
data class Summary(
    val Countries: List<Country>,
    val Date: String,
    val Global: Global
)
/**
 * Statistics per country
 * Get Country name and TotalConfirmed
 */

data class Country(
    val Country: String,
    val CountryCode: String,
    val Date: String,
    val NewConfirmed: Int,
    val NewDeaths: Int,
    val NewRecovered: Int,
    val Slug: String,
    val TotalConfirmed: Int,
    val TotalDeaths: Int,
    val TotalRecovered: Int
)
//Global statistics
data class Global(
    val NewConfirmed: Int,
    val NewDeaths: Int,
    val NewRecovered: Int,
    val TotalConfirmed: Int,
    val TotalDeaths: Int,
    val TotalRecovered: Int
)

//Get a list of all countries

//then get the statistics per country