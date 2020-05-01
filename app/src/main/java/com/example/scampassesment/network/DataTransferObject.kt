package com.example.scampassesment.network

import com.example.scampassesment.database.DatabaseCountry
import com.example.scampassesment.model.Country

data class NetworkStatisticsContainer(val countries: List<Country>)
/**
 * Convert Network results to database objects
 */
    fun NetworkStatisticsContainer.asDatabaseModel(): List<DatabaseCountry> {
        return countries.map {
            DatabaseCountry(
                Country=it.Country,
                CountryCode=it.CountryCode!!,
                Date= it.Date!!,
                NewConfirmed=it.NewConfirmed!!,
                NewDeaths=it.NewDeaths!!,
                NewRecovered=it.NewRecovered!!,
                Slug=it.Slug!!,
                TotalConfirmed=it.TotalConfirmed!!,
                TotalDeaths=it.TotalDeaths!!,
                TotalRecovered=it.TotalRecovered!!
            )
        }
    }
