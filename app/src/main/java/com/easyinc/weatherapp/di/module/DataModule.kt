package com.easyinc.weatherapp.di.module

import android.app.Application
import androidx.room.Room
import com.easyinc.weatherapp.cache.dao.WeatherDao
import com.easyinc.weatherapp.cache.db.WeatherDatabase
import com.easyinc.weatherapp.data.repository.WeatherRepositoryImpl
import com.easyinc.weatherapp.domain.IWeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): WeatherDatabase{
        return Room
            .databaseBuilder(application,WeatherDatabase::class.java, "WeatherApp")
            .build()
    }

    @Singleton
    @Provides
    fun provideFavouriteDao(appDatabase: WeatherDatabase): WeatherDao{
        return appDatabase.weatherDao()
    }



}