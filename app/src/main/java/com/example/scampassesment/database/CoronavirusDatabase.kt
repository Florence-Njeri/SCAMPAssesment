package com.example.scampassesment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.scampassesment.model.Country

/**
 * The Room database uses the DAO to issue queries to the SQLite database.
 */

@Database(entities = [Country::class], version = 2, exportSchema = false)
abstract class CoronavirusDatabase : RoomDatabase(){
    abstract val coronavirusDatabaseDao:CoronaVirusDao

    companion object {

        @Volatile
        private var INSTANCE: CoronavirusDatabase? = null

        fun getInstance(context: Context): CoronavirusDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoronavirusDatabase::class.java,
                        "coronavirus_statistics_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }

}