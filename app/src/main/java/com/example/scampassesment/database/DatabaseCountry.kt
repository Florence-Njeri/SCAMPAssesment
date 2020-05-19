package com.example.scampassesment.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 */
@Entity(tableName = "statistics_table")
data class DatabaseCountry (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Country")
    val Country: String="",
    @ColumnInfo(name = "CountryCode")
    val CountryCode: String?=null,
    @ColumnInfo(name = "Date")
    val Date: String?=null,
    @ColumnInfo(name = "NewConfirmed")
    val NewConfirmed: Int?=null,
    @ColumnInfo(name = "NewDeaths")
    val NewDeaths: Int?=null,
    @ColumnInfo(name = "NewRecovered")
    val NewRecovered: Int?=null,
    @ColumnInfo(name = "Slug")
    val Slug: String?=null,
    @ColumnInfo(name = "TotalConfirmed")
    val TotalConfirmed: Int?=null,
    @ColumnInfo(name = "TotalDeaths")
    val TotalDeaths: Int?=null,
    @ColumnInfo(name = "TotalRecovered")
    val TotalRecovered: Int?=null

)