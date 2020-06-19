package com.easyinc.weatherapp.di.module

import com.easyinc.weatherapp.cache.WeatherCacheImpl
import com.easyinc.weatherapp.data.repository.WeatherCache
import com.easyinc.weatherapp.data.repository.WeatherRepositoryImpl
import com.easyinc.weatherapp.domain.IWeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): IWeatherRepository

    @Binds
    abstract fun provideWeatherCache(weatherCache: WeatherCacheImpl): WeatherCache

}