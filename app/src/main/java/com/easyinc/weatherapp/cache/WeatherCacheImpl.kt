package com.easyinc.weatherapp.cache

import com.easyinc.weatherapp.cache.dao.WeatherDao
import com.easyinc.weatherapp.model.*
import com.easyinc.weatherapp.common.extentions.batch
import com.easyinc.weatherapp.data.repository.WeatherCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherCacheImpl @Inject constructor(private val weatherDao: WeatherDao): WeatherCache {

    override fun addCity(city: StartCity): Completable {
        return weatherDao.setCity(city.city!!).flatMap { cityId ->
            weatherDao.setSeasons(city.seasons.map {
                it.cityId = cityId
                it })
        }.flatMap {seasonIds ->
            val newMonths = divideList(city.months,seasonIds)
            weatherDao.setMonths(newMonths)
        }.flatMapCompletable {
            Completable.complete()
        }
    }

    override fun getCityDetails(cityName: String, season: String): Single<FinalCity> {
        val finalCity: FinalCity = FinalCity()
        return weatherDao.fetchCity(cityName).flatMap {city ->
            finalCity.city = city
            weatherDao.fetchSeason(city.id,season)
        }.flatMap {mSeason ->
            finalCity.season = mSeason
            weatherDao.fetchMonths(mSeason.id)
        }.map {months ->
            finalCity.months = months
        }.flatMap {
            Single.just(finalCity)
        }
    }

    override fun getCityToUpdate(cityName: String): Single<StartCity> {
        val mStartCity: StartCity = StartCity()
        return weatherDao.fetchCity(cityName).flatMap {city ->
            mStartCity.city = city
            weatherDao.fetchSeasonsToUpdate(city.id)
        }.flatMap {mSeasons ->
            mStartCity.seasons = mSeasons
            weatherDao.fetchMonthsToUpdate(mSeasons[0].id,mSeasons[1].id,mSeasons[2].id,mSeasons[3].id)
        }.map {months ->
            mStartCity.months = months
        }.flatMap {
            Single.just(mStartCity)
        }
    }

    override fun updateCity(city: StartCity): Completable {
        return weatherDao.updateCity(city.city!!).doOnComplete {
            weatherDao.updateSeasons(city.seasons).subscribe()
        }.doOnComplete {
            weatherDao.updateMonths(city.months).subscribe()
        }
    }

    override fun deleteCity(city: StartCity): Completable {
        return weatherDao.deleteCity(city.city!!).doOnComplete {
            weatherDao.deleteSeasons(city.seasons)
        }.doOnComplete {
            weatherDao.deleteMonths(city.months)
        }
    }

    override fun fetchCitiesNames(): Observable<List<String>> {
        return weatherDao.fetchCitiesNames()
    }

    private fun mapMonthsList(list: List<MonthEntity>, seasonId: Long): List<MonthEntity>{
        return list.map {
            it.seasonId = seasonId
            it
        }
    }

    private fun divideList(months: List<MonthEntity>, seasonIds: List<Long>): List<MonthEntity>{
        val list = months.batch(3)

        var winterList = list[0]
        var springList = list[1]
        var summerList = list[2]
        var autumnList = list[3]

        winterList = mapMonthsList(winterList,seasonIds[0])
        springList = mapMonthsList(springList,seasonIds[1])
        summerList = mapMonthsList(summerList,seasonIds[2])
        autumnList = mapMonthsList(autumnList,seasonIds[3])

        return winterList+springList+summerList+autumnList
    }

}