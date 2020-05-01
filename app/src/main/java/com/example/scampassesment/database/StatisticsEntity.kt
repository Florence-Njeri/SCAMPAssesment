package com.example.scampassesment.database

import com.example.scampassesment.model.Country

/**
 * Map DatabaseCountry objects to domain entities
 */
fun List<DatabaseCountry>.asDomainModel(): List<Country> {
    return map {
        Country(
            Country = it.Country,
            CountryCode = it.CountryCode!!,
            Date = it.Date!!,
            NewConfirmed = it.NewConfirmed!!,
            NewDeaths = it.NewDeaths!!,
            NewRecovered = it.NewRecovered!!,
            Slug = it.Slug!!,
            TotalConfirmed = it.TotalConfirmed!!,
            TotalDeaths = it.TotalDeaths!!,
            TotalRecovered = it.TotalRecovered!!
        )
    }
}