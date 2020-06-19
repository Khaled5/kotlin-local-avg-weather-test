package com.easyinc.weatherapp.data.repository

import com.easyinc.weatherapp.model.FinalCity
import com.easyinc.weatherapp.model.StartCity
import com.easyinc.weatherapp.domain.IWeatherRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherCache: WeatherCache): IWeatherRepository {
    override fun addCity(city: StartCity): Completable {
        return weatherCache.addCity(city)
    }

    override fun getCityDetails(cityName: String, season: String): Single<FinalCity> {
        return weatherCache.getCityDetails(cityName,season)
    }

    override fun getCityToUpdate(cityName: String): Single<StartCity> {
        return weatherCache.getCityToUpdate(cityName)
    }

    override fun updateCity(city: StartCity): Completable {
        return weatherCache.updateCity(city)
    }

    override fun deleteCity(city: StartCity): Completable {
        return weatherCache.deleteCity(city)
    }

    override fun getCitiesName(): Observable<List<String>> {
        return weatherCache.fetchCitiesNames()
    }
}