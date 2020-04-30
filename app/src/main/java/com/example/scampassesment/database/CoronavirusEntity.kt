package com.example.scampassesment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statistics_table")
data class CoronavirusEntity (
    @PrimaryKey
    @ColumnInfo(name = "Country")
    val Country: String,
    @ColumnInfo(name = "CountryCode")
    val CountryCode: String,
    @ColumnInfo(name = "Date")
    val Date: String,
    @ColumnInfo(name = "NewConfirmed")
    val NewConfirmed: Int,
    @ColumnInfo(name = "NewDeaths")
    val NewDeaths: Int,
    @ColumnInfo(name = "NewRecovered")
    val NewRecovered: Int,
    @ColumnInfo(name = "Slug")
    val Slug: String,
    @ColumnInfo(name = "TotalConfirmed")
    val TotalConfirmed: Int,
    @ColumnInfo(name = "TotalDeaths")
    val TotalDeaths: Int,
    @ColumnInfo(name = "TotalRecovered")
    val TotalRecovered: Int

)