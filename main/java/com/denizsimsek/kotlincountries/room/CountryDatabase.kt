package com.denizsimsek.kotlincountries.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denizsimsek.kotlincountries.util.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    /*Using singleton
        *1->so that only one db object initialezes in app
        *2->other threads can not reach these object at a time so that singleton preventes from conflict
    */
    companion object
    {
        @Volatile var instance:CountryDatabase?=null//volatile->makes object visible for other threads
        val lock=Any()//lock->checks if instance is locked with another thread
        operator fun invoke(context: Context)= instance ?: synchronized(lock)
        {
            instance ?: makeDatabase(context).also {
                instance=it
            }
        }
        private fun makeDatabase(context: Context)= Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,"country").build()

    }
}
