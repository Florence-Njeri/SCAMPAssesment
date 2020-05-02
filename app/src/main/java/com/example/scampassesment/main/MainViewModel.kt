package com.example.scampassesment.main

import android.app.Application
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

    private val statisticsDatabase = getInstance(application)
    val statisticsRepository = StatisticsRepository(statisticsDatabase, application)
//    var statistics = database.getSummaryStatistics()

    /**
     * The data source this ViewModel will fetch results from.
     */

//    val sharedPreference =application.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
//    val recovered =sharedPreference.getString("newRecovered"," ")
//    Log.d("Recovered",recovered)
    val statistics = statisticsRepository.coronavirusStatistics

    //    val newConfirmed = sharedPreference.getString("newConfirmed", "")
//    val newDeaths = statisticsRepository.newDeaths
//    val newRecovered = statisticsRepository.newRecovered
//    val totalConfirmed = statisticsRepository.totalConfirmed
//    val totalDeaths = statisticsRepository.totalDeaths
//    val totalRecovered = statisticsRepository.totalRecovered
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
//            try {
//                statisticsRepository.refreshStatistics()
////                _eventNetworkError.value = false
////                _isNetworkErrorShown.value = false
//
//            } catch (networkError: IOException) {
//                // Show a Toast error message and hide the progress bar.
//                if (statistics.value.isNullOrEmpty())
//                    statisticsRepository.refreshStatistics()
////                    _eventNetworkError.value = true
//
//            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
