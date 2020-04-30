package com.example.scampassesment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CoronaVirusDao {
    @Insert
    fun insert(summary: CoronavirusEntity)

    @Update
    fun update(summary: CoronavirusEntity)

    @Query("SELECT * from statistics_table ORDER BY TotalConfirmed DESC")
    fun getSummaryStatistics(): LiveData<List<CoronavirusEntity>>
}