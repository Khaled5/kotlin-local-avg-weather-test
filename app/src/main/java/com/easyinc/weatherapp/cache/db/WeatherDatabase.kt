package com.easyinc.weatherapp.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.easyinc.weatherapp.cache.dao.WeatherDao
import com.easyinc.weatherapp.model.CityEntity
import com.easyinc.weatherapp.model.MonthEntity
import com.easyinc.weatherapp.model.SeasonEntity

@Database(entities = [CityEntity::class,SeasonEntity::class,MonthEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){

    abstract fun weatherDao(): WeatherDao

}