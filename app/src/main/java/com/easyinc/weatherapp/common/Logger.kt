package com.easyinc.weatherapp.common

import android.util.Log

object Logger {

    private const val TAG = "WeatherZ"

    fun dt(value: String) {
        Log.d(TAG, "Thread Name: ${Thread.currentThread().name} - $value")
    }
}