package com.example.scampassesment.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.scampassesment.api.CoronavirusStatisticsRetriever
import com.example.scampassesment.database.CoronavirusDatabase
import com.example.scampassesment.model.Country
import com.example.scampassesment.model.Summary
import com.example.scampassesment.ui.view.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository for fetching Coronavirus statistics from the network and caching them in Room
 */

class StatisticsRepository(private var database: CoronavirusDatabase, val context: Context) {

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
                    Log.e("MainActivity", "Problem calling Covid API: {${t?.message}}")
                }

                override fun onResponse(call: Call<Summary>, response: Response<Summary>) {
                    response.isSuccessful.let {

                        Log.d("Global Statistics", response.body()?.Global.toString())

                        val totalConfirmed =
                            String.format("%,d", response.body()?.Global?.TotalConfirmed)

                        val totalRecovered =
                            String.format("%,d", response.body()?.Global?.TotalRecovered)

                        val totalDeaths = String.format("%,d", response.body()?.Global?.TotalDeaths)
                        val mainActivity =
                            MainActivity()
                        //Save Global Statistics to shared Preferences
                        val sharedPreference =
                            context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                        var editor = sharedPreference.edit()
                        editor.putString(
                            "newConfirmed",
                            response.body()?.Global?.NewConfirmed.toString()
                        )
                        editor.putString(
                            "newDeaths",
                            response.body()?.Global?.NewDeaths.toString()
                        )
                        editor.putString(
                            "newRecovered",
                            response.body()?.Global?.NewRecovered.toString()
                        )
                        editor.putString(
                            "totalConfirmed",
                            totalConfirmed
                        )
                        editor.putString(
                            "totalDeaths",
                            totalDeaths
                        )
                        editor.putString(
                            "totalRecovered",
                            totalRecovered
                        )
                        editor.apply()

                        sharedPreference.getString("newRecovered", " ")
                        Log.d("WorldSummary:", response.body().toString())
                        val resultList = response.body()

                        Thread {
                            //Do your database´s operations here
                            if (resultList?.Countries != null) {
                                database.coronavirusDatabaseDao.insertAll(resultList.Countries)

                            }

                        }.start()

                    }
                }


            })

        }
    }
}