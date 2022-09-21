package com.denizsimsek.kotlincountries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreferences
{
    companion object
    {
        private var sharredPreferences:SharedPreferences?=null
        private val PREFERENCES_TIME = "preferences_time"
        @Volatile var instance:CustomSharedPreferences?=null
        val lock=Any()
        operator fun invoke(context: Context):CustomSharedPreferences= instance?: synchronized(lock)
        {
            instance?:makeCSP(context).also {
                instance=it
            }
        }
        private fun makeCSP(context:Context):CustomSharedPreferences
        {
            sharredPreferences=PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time : Long)
    {
        sharredPreferences?.edit(commit = true)
        {
            putLong(PREFERENCES_TIME,time)
        }
    }
    fun getTime() = sharredPreferences?.getLong(PREFERENCES_TIME,0)

}
