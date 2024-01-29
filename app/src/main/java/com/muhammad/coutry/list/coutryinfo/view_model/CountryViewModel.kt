package com.muhammad.coutry.list.coutryinfo.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammad.coutry.list.coutryinfo.model.CountryModel
import com.muhammad.coutry.list.coutryinfo.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application:Application) : BaseViewModel(application) {

     val countryLiveData = MutableLiveData<CountryModel>()

     fun getFromDatabase(uuid:Int) {

         launch {

             val dao = CountryDatabase(getApplication()).countryDao()

             countryLiveData.value = dao.getCountry(uuid)
             
         }
     }
}