package com.easyinc.weatherapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.easyinc.weatherapp.R
import com.easyinc.weatherapp.common.base.BaseActivity
import com.easyinc.weatherapp.common.extentions.snack
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host_fragment)
        b.setupWithNavController(navController)

        observeMessage()

    }

    fun showSnackbar(message: String){
        weather_snack.snack(message)
    }

    private fun observeMessage(){
        mainViewModel.observeMessage().observe(this, Observer {
            weather_snack.snack(it)
        })
    }
}