package com.denizsimsek.kotlincountries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.denizsimsek.kotlincountries.R

class MainActivity : AppCompatActivity() {
    private lateinit var navhost:NavHostFragment
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navhost=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navhost.navController


    }
}
