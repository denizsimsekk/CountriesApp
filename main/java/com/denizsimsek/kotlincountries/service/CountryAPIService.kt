package com.denizsimsek.kotlincountries.service

import com.denizsimsek.kotlincountries.util.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    private val BASE_URL="https://raw.githubusercontent.com/"
    private var api= Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL).build().create(CountryAPI::class.java)
    fun getData():Single<List<Country>>
    {
        return api.getAll()
    }
}
