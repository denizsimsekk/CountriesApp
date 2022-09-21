package com.denizsimsek.kotlincountries.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.denizsimsek.kotlincountries.util.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>

    @Query("SELECT * FROM country")
    suspend fun selectAll():List<Country>

    @Query("SELECT * FROM country WHERE uuid = :id")
    suspend fun getCountry(id:Int):Country

    @Query("DELETE FROM country")
    suspend fun deleteAll()

}
