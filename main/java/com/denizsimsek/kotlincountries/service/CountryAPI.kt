package com.denizsimsek.kotlincountries.service

import com.denizsimsek.kotlincountries.util.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getAll():Single<List<Country>>
    //observable ->sürekli veri alır
    //single istek yapıldıkça veriyi alır

}
