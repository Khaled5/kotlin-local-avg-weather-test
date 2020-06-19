package com.easyinc.weatherapp.data.repository

import com.easyinc.weatherapp.model.FinalCity
import com.easyinc.weatherapp.model.StartCity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherCache {

    fun addCity(city: StartCity): Completable

    fun getCityDetails(cityName: String, season: String): Single<FinalCity>

    fun getCityToUpdate(cityName: String): Single<StartCity>

    fun updateCity(city: StartCity): Completable

    fun deleteCity(city: StartCity): Completable

    fun fetchCitiesNames(): Observable<List<String>>

}