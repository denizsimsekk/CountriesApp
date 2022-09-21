package com.denizsimsek.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.denizsimsek.kotlincountries.room.CountryDatabase
import com.denizsimsek.kotlincountries.util.Country
import kotlinx.coroutines.launch

class DetailseViewModel(application: Application) :BaseViewModel(application) {

     var selectedCountry=MutableLiveData<Country>()


    fun getDataFromRoom(id:Int)
    {
        launch {
            var dao=CountryDatabase(getApplication()).countryDao()
            var country=dao.getCountry(id)
            selectedCountry.value=country
        }

    }

}
