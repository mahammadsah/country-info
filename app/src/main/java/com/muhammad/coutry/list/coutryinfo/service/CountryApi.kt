package com.muhammad.coutry.list.coutryinfo.service

import com.muhammad.coutry.list.coutryinfo.model.CountryModel
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<ArrayList<CountryModel>>
}