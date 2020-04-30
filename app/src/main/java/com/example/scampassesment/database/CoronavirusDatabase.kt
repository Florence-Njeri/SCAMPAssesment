package com.example.scampassesment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoronavirusEntity::class], version = 1, exportSchema = false)
abstract class CoronavirusDatabase : RoomDatabase(){
    abstract val coronavirusDatabase:CoronaVirusDao

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