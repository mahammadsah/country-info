package com.muhammad.coutry.list.coutryinfo.service

import com.muhammad.coutry.list.coutryinfo.model.CountryModel
import com.muhammad.coutry.list.coutryinfo.view_model.CountryViewModel
import com.muhammad.coutry.list.coutryinfo.view_model.FeedViewModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.reflect.Proxy

class CountryApiService {

    companion object {

        const val BASE_URL:String = "https://raw.githubusercontent.com/"
    }
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryApi::class.java)

    fun getData() : Single<ArrayList<CountryModel>>{

        return api.run {

            getCountries()
        }
    }
}