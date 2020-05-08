package com.example.scampassesment.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.scampassesment.model.Country

@Dao
interface CoronaVirusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(summary: List<Country>?)

    @Update
    fun update(summary: Country)
    //Get a specific country based on user Query

    @Query("SELECT Country from statistics_table ORDER BY Country DESC LIMIT 1")
    fun getCountry(/*country: String*/): LiveData<Country>


    @Query("SELECT * from statistics_table ORDER BY TotalConfirmed DESC")
    fun getSummaryStatistics(): LiveData<List<Country>>
}