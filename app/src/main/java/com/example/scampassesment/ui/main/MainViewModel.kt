package com.example.scampassesment.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scampassesment.database.CoronaVirusDao
import com.example.scampassesment.model.Country
import kotlinx.coroutines.*

class MainViewModel(val database: CoronaVirusDao, application: Application) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var countryStatistic = MutableLiveData<Country?>()

    var statistics=database.getSummaryStatistics()

    init {
        uiScope.launch {
//            insert()

            countryStatistic.value = getStatisticsFromDatabase()
        }
    }

    private suspend fun getStatisticsFromDatabase(): Country? {
        return withContext(Dispatchers.IO) {
            database.getCountry()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
