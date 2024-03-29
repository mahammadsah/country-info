package com.muhammad.coutry.list.coutryinfo.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muhammad.coutry.list.coutryinfo.model.CountryModel

@Database(entities = [CountryModel::class], version = 2)
abstract class CountryDatabase() : RoomDatabase() {

    abstract fun countryDao() : CountryDao


    companion object {

        @Volatile private var instance:CountryDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()){

            instance ?: makeDatabase(context).also{
                instance = it
            }
        }

        private fun makeDatabase(context:Context) : CountryDatabase {

            return Room.databaseBuilder(context.applicationContext,
                CountryDatabase::class.java, "countries").build()
        }
    }
}