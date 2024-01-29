package com.muhammad.coutry.list.coutryinfo.view_model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammad.coutry.list.coutryinfo.model.CountryModel
import com.muhammad.coutry.list.coutryinfo.service.CountryApiService
import com.muhammad.coutry.list.coutryinfo.service.CountryDatabase
import com.muhammad.coutry.list.coutryinfo.service.DataManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.jvm.Throws

class FeedViewModel(application: Application) : BaseViewModel(application) {

    val feedData = MutableLiveData<List<CountryModel>>()

    val feedError = MutableLiveData<Boolean>()

    val feedProgress = MutableLiveData<Boolean>()

    val dataManager:DataManager = DataManager.invoke(application)

    private var disposable : CompositeDisposable? = CompositeDisposable()

    private var timeRefresh = 0.2 * 60 * 1000 * 1000 * 1000L

    fun refreshData(){

        val updateTime = dataManager.getTime()

        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < timeRefresh){

            getDataFromSQLLite()
        }else{

            getDataFromApi()
        }
    }

    private fun getDataFromSQLLite(){

        launch {

            val countries = CountryDatabase(getApplication()).countryDao().getCountries()

            showCountries(countries)

            Toast.makeText(getApplication(), "from sq lite ", Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromApi(){

        feedProgress.value = true

        try{

            disposable?.run {
                add(CountryApiService().getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object: DisposableSingleObserver<ArrayList<CountryModel>>(){
                            override fun onSuccess(t: ArrayList<CountryModel>) {

                                putDataInLocalDatabase(t)

                                Toast.makeText(getApplication(), "from API ", Toast.LENGTH_LONG).show()
                            }

                            override fun onError(e: Throwable) {

                                feedProgress.value = false

                                feedError.value = true
                            }
                        }))
            }


        }catch(ex:Exception){


            Toast.makeText(getApplication(), ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showCountries(countryList:List<CountryModel>) {

        feedData.value = countryList

        feedProgress.value = false

        feedError.value = false
    }

    private fun putDataInLocalDatabase(countryList:ArrayList<CountryModel>) {

        launch {

                val doa = CountryDatabase(getApplication()).countryDao()

                doa.deleteAllCountry()

            try{

                val idList =  doa.addAllCountry(*countryList.toTypedArray())

                var index = 0

                while (index < idList.size) {

                    countryList[index].uuid = idList[index].toInt()
                    index++
                }

            }catch(ex:Exception){

                Toast.makeText(getApplication(), ex.message, Toast.LENGTH_LONG).show()
            }



                dataManager.saveTime(System.nanoTime())

                showCountries(countryList)
        }
    }

    override fun onCleared() {
        super.onCleared()

        disposable?.run {

            clear()

            disposable = null
        }
    }
}