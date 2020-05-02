package com.example.scampassesment.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scampassesment.model.Country

class DetailsViewModelFactory(
    private val countryProperty: Country,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryStatisticsDetailsViewModel::class.java)) {
            return CountryStatisticsDetailsViewModel(countryProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}