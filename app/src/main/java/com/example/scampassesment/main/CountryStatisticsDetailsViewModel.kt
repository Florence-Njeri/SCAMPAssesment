package com.example.scampassesment.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scampassesment.model.Country

class CountryStatisticsDetailsViewModel(
    countryProperty: Country,
    application: Application
) : ViewModel() {

    private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country>
        get() = _selectedCountry

    init {
        _selectedCountry.value = countryProperty
    }
}
