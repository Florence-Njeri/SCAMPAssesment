package com.example.scampassesment.network

import com.example.scampassesment.database.DatabaseCountry
import com.example.scampassesment.model.Country
import com.example.scampassesment.model.NetworkCountry
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkStatisticsContainer(val countries: List<NetworkCountry>)
/**
 * Convert Network results to database objects
 */
fun NetworkStatisticsContainer.asDomainModel(): List<Country> {
        return countries.map {
            Country(
                Country=it.Country,
                CountryCode = it.CountryCode,
                Date = it.Date,
                NewConfirmed = it.NewConfirmed,
                NewDeaths = it.NewDeaths,
                NewRecovered = it.NewRecovered,
                Slug = it.Slug,
                TotalConfirmed = it.TotalConfirmed,
                TotalDeaths = it.TotalDeaths,
                TotalRecovered = it.TotalRecovered
            )
        }
}

fun NetworkStatisticsContainer.asDatabaseModel(): List<DatabaseCountry> {
    return countries.map {
        DatabaseCountry(
            Country = it.Country,
            CountryCode = it.CountryCode,
            Date = it.Date,
            NewConfirmed = it.NewConfirmed,
            NewDeaths = it.NewDeaths,
            NewRecovered = it.NewRecovered,
            Slug = it.Slug,
            TotalConfirmed = it.TotalConfirmed,
            TotalDeaths = it.TotalDeaths,
            TotalRecovered = it.TotalRecovered
        )
    }
}
