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

//Get a list of all countries

//then get the statistics per country