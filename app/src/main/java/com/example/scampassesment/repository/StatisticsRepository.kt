package com.example.scampassesment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.scampassesment.api.CoronavirusStatisticsRetriever
import com.example.scampassesment.database.CoronavirusDatabase
import com.example.scampassesment.database.asDomainModel
import com.example.scampassesment.model.Country
import com.example.scampassesment.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

/**
 * Repository for fetching coronavirus statistics from the network and storing them on disk
 */
class StatisticsRepository(private var database: CoronavirusDatabase) {

    val coronavirusStatistics: LiveData<List<Country>> =
        Transformations.map(database.coronavirusDatabaseDao.getSummaryStatistics()) {
            it.asDomainModel()

        }

    /**
     *  API used to refresh the offline cache.
     */

    suspend fun refreshStatistics() {
        withContext(Dispatchers.IO) {

            val playlist = CoronavirusStatisticsRetriever.service.getWorldSummary().await()
            database.coronavirusDatabaseDao.insertAll(playlist.asDatabaseModel())
        }
    }
}