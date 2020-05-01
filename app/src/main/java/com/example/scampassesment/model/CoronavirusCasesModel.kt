package com.example.scampassesment.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "statistics_table")
data class Country(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Country")
    val Country: String = "",
    @ColumnInfo(name = "CountryCode")
    val CountryCode: String? = null,
    @ColumnInfo(name = "Date")
    val Date: String? = null,
    @ColumnInfo(name = "NewConfirmed")
    val NewConfirmed: Int? = null,
    @ColumnInfo(name = "NewDeaths")
    val NewDeaths: Int? = null,
    @ColumnInfo(name = "NewRecovered")
    val NewRecovered: Int? = null,
    @ColumnInfo(name = "Slug")
    val Slug: String? = null,
    @ColumnInfo(name = "TotalConfirmed")
    val TotalConfirmed: Int? = null,
    @ColumnInfo(name = "TotalDeaths")
    val TotalDeaths: Int? = null,
    @ColumnInfo(name = "TotalRecovered")
    val TotalRecovered: Int? = null
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