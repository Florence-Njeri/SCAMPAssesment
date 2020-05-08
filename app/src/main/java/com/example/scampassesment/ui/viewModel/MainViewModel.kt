package com.example.scampassesment.ui.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scampassesment.database.CoronavirusDatabase.Companion.getInstance
import com.example.scampassesment.model.Country
import com.example.scampassesment.repository.StatisticsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {


    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var countryStatistic = MutableLiveData<Country?>()

    private val _navigateToSelectedCountry = MutableLiveData<Country>()
    val navigateToSelectedCountry: LiveData<Country>
        get() = _navigateToSelectedCountry

    private val statisticsDatabase = getInstance(application)
    val statisticsRepository = StatisticsRepository(statisticsDatabase, application)
//    var statistics = database.getSummaryStatistics()

    /**
     * The data source this ViewModel will fetch results from.
     */

    val statistics = statisticsRepository.coronavirusStatistics

    init {
        uiScope.launch {

            refreshDataFromRepository()

        }
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            statisticsRepository.refreshStatistics()

        }
    }

    fun displayPropertyDetails(countryProperty: Country) {
        _navigateToSelectedCountry.value = countryProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedCountry.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
