package com.example.scampassesment.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.scampassesment.model.Country

@Dao
interface CoronaVirusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(summary: DatabaseCountry)

    @Update
    fun update(summary: DatabaseCountry)
    //Get a specific country based on user Query

    @Query("SELECT * from statistics_table ORDER BY Country DESC LIMIT 1")
    fun getCountry(): Country?


    @Query("SELECT * from statistics_table ORDER BY TotalConfirmed DESC")
    fun getSummaryStatistics(): LiveData<List<DatabaseCountry>>
}