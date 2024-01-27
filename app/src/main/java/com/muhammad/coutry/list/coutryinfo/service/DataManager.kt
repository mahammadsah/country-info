package com.muhammad.coutry.list.coutryinfo.service

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class DataManager {

    companion object {

        const val TIME_DATA_KEY:String = "time_key"

        private var sharedPreferences:SharedPreferences? = null

        @Volatile private var instance:DataManager? = null

        operator fun invoke (context:Context) : DataManager = instance ?: synchronized(Any()){

            instance ?: makeDataManager(context).also {

                instance = it
            }
        }

        private fun makeDataManager(context:Context) : DataManager {

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            return DataManager()
        }
    }

    fun saveTime(time:Long){

        sharedPreferences?.edit(commit = true){

            putLong(TIME_DATA_KEY , time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(TIME_DATA_KEY, 0)
}