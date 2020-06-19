package com.easyinc.weatherapp.cache.dao

import androidx.room.*
import com.easyinc.weatherapp.model.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface WeatherDao {

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCity(city: CityEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSeasons(seasons: List<SeasonEntity>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMonths(months: List<MonthEntity>): Single<List<Long>>

    //Update
    @Update
    fun updateCity(city: CityEntity): Completable

    @Update
    fun updateSeasons(seasons: List<SeasonEntity>): Completable

    @Update
    fun updateMonths(months: List<MonthEntity>): Completable

    //Delete

    @Delete
    fun deleteCity(city: CityEntity): Completable

    @Delete
    fun deleteSeasons(seasons: List<SeasonEntity>): Completable

    @Delete
    fun deleteMonths(months: List<MonthEntity>): Completable

    //Select

    @Query("select * from city where name = :name")
    fun fetchCity(name :String): Single<CityEntity>

    @Query("select name from city")
    fun fetchCitiesNames(): Observable<List<String>>

    @Query("select * from season where cityId = :id and name = :season")
    fun fetchSeason(id:Long,season: String): Single<SeasonEntity>

    @Query("select * from season where cityId = :id")
    fun fetchSeasonsToUpdate(id:Long): Single<List<SeasonEntity>>

    @Query("select * from month where seasonId = :id")
    fun fetchMonths(id:Long): Single<List<MonthEntity>>

    @Query("select * from month where seasonId = :seasonOneId or seasonId = :seasonTwoId or seasonId = :seasonThreeId or seasonId = :seasonFourId")
    fun fetchMonthsToUpdate(seasonOneId :Long, seasonTwoId :Long,
                            seasonThreeId :Long, seasonFourId :Long): Single<List<MonthEntity>>
}