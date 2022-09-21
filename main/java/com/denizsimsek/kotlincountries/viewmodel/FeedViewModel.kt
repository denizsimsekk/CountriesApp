package com.denizsimsek.kotlincountries.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.denizsimsek.kotlincountries.room.CountryDatabase
import com.denizsimsek.kotlincountries.service.CountryAPIService
import com.denizsimsek.kotlincountries.util.Country
import com.denizsimsek.kotlincountries.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {
    private val countryApiService=CountryAPIService()
    private val disposable=CompositeDisposable()
    val customSP= CustomSharedPreferences(getApplication())
    var countries= MutableLiveData<List<Country>>()
    var countryError=MutableLiveData<Boolean>()
    var countryLoading=MutableLiveData<Boolean>()
    var refreshTime=10*60*1000*1000*1000L

    fun refreshData()
    {

        val updateTime=customSP.getTime()
        println("update time: "+updateTime)
        if(updateTime!=null && updateTime!=0L && System.nanoTime()-updateTime<refreshTime)
        {
            getDataFromSQL()
        }
        else
        {
            getDataFromApi()
        }
    }

    private fun getDataFromSQL()
    {
        launch {
            val dao=CountryDatabase(getApplication()).countryDao()
            var newList=dao.selectAll()
            showCountries(newList)
        }
        Toast.makeText(getApplication() ,"SQL",Toast.LENGTH_LONG).show()
    }
    fun getDataFromApi()
    {
        disposable.add(countryApiService.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(t: List<Country>) {
                    saveToSQLite(t)
                    Toast.makeText(getApplication() ,"API",Toast.LENGTH_LONG).show()
                }

                override fun onError(e: Throwable) {

                    countryError.value=true
                    countryLoading.value=false
                    e.printStackTrace()
                }

            })
        )
    }
    private fun saveToSQLite(list:List<Country>)
    {

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAll()
            var uuidList=dao.insertAll(*list.toTypedArray())
            var i=0
            while(i<list.size)
            {
                list[i].uuid=uuidList[i].toInt()
                i++
            }
            showCountries(list)
        }
        customSP.saveTime(System.nanoTime())
        println("input time: "+ customSP.getTime())
    }
    private fun showCountries(list:List<Country>)
    {
        countries.value=list
        countryError.value=false
        countryLoading.value=false
    }
}
