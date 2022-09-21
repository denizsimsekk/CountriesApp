package com.denizsimsek.kotlincountries.util

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @SerializedName("name")
    var countryName:String,
    @SerializedName("capital")
   var countryCapital:String,
    @SerializedName("region")
   var countryRegion:String,
    @SerializedName("currency")
   var countryCurrency:String,
    @SerializedName("language")
   var countyrLanguage:String,
    @SerializedName("flag")
   var countryImage:String)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
