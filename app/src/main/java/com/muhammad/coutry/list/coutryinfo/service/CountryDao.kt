package com.muhammad.coutry.list.coutryinfo.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.muhammad.coutry.list.coutryinfo.model.CountryModel

@Dao
interface CountryDao {

    @Insert
    suspend fun addAllCountry(vararg country:CountryModel) : List<Long>

    @Insert
    suspend fun addCountry(country:CountryModel) : Long

    @Query("select * from countrymodel")
    suspend fun getCountries() : List<CountryModel>

    @Query("select * from countrymodel where uuid = :id")
    suspend fun getCountry (id:Int) : CountryModel

    @Query("delete from countrymodel")
    suspend fun deleteAllCountry()
}