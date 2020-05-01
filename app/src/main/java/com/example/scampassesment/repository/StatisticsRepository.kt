package com.example.scampassesment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.scampassesment.api.CoronavirusStatisticsRetriever
import com.example.scampassesment.database.CoronavirusDatabase
import com.example.scampassesment.model.Country
import com.example.scampassesment.model.Summary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository for fetching coronavirus statistics from the network and storing them on disk
 */
class StatisticsRepository(private var database: CoronavirusDatabase) {

    var client = CoronavirusStatisticsRetriever.service

    val coronavirusStatistics: LiveData<List<Country>> =
        (database.coronavirusDatabaseDao.getSummaryStatistics())



    /**
     *  API used to refresh the offline cache.
     */


    suspend fun refreshStatistics() {
        withContext(Dispatchers.IO) {

            client.getWorldSummary().enqueue(object : Callback<Summary> {
                override fun onFailure(call: Call<Summary>?, t: Throwable?) {
                    Log.e("MainActivity", "Problem calling Github API {${t?.message}}")
                }

                override fun onResponse(call: Call<Summary>, response: Response<Summary>) {
                    response.isSuccessful.let {
                        Log.d("WorldSummary:", response.body().toString())
                        val resultList = response.body()
//                Log.d("WorldSummarySize:", resultList?.Countries?.size .toString())
//                Log.d("WorldSummarySizeResult:", mutableListOf(resultList).size .toString())
//
//                var adapter = CountriesStatisticsAdapter(ClickListener {
//
//                })
//                countriesList.adapter=adapter
//                adapter.submitList(resultList?.Countries)

                        Thread {
                            //Do your database´s operations here
                            database.coronavirusDatabaseDao.insertAll(resultList?.Countries)

                        }.start()

                    }
                }


            })

        }
    }
}