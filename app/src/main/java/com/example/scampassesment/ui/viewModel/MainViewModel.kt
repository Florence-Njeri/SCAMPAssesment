package com.example.scampassesment.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scampassesment.model.Country
import com.example.scampassesment.model.LoadingState
import com.example.scampassesment.repository.StatisticsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val statisticsRepository: StatisticsRepository) : ViewModel() {


    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var countryStatistic = MutableLiveData<Country?>()
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _navigateToSelectedCountry = MutableLiveData<Country>()
    val navigateToSelectedCountry: LiveData<Country>
        get() = _navigateToSelectedCountry

//    val statisticsRepository = StatisticsRepository(statisticsDatabase, application)
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

            try {
                _loadingState.value = LoadingState.LOADING
                statisticsRepository.refreshStatistics()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }

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
