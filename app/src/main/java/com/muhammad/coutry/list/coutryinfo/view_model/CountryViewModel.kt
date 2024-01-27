package com.muhammad.coutry.list.coutryinfo.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammad.coutry.list.coutryinfo.model.CountryModel

class CountryViewModel(application:Application) : BaseViewModel(application) {

    companion object {

        const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    val countryInfo = MutableLiveData<CountryModel>()

    fun getDataFromOtherFragment(data:String){

        when(data){

            data ->  "0"

        }
    }
}