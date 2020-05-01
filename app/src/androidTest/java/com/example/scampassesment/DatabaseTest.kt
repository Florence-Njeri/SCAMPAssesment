package com.example.scampassesment

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.scampassesment.database.CoronaVirusDao
import com.example.scampassesment.database.CoronavirusDatabase
import com.example.scampassesment.database.DatabaseCountry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var coronavirusDao: CoronaVirusDao
    private lateinit var db: CoronavirusDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, CoronavirusDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .build()
        coronavirusDao = db.coronavirusDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetRecipe() {
        val recipe = DatabaseCountry()
        coronavirusDao.insertAll(recipe)
        val tonight = coronavirusDao.getSummaryStatistics()
        Assert.assertEquals(tonight.value, null)
    }
}

