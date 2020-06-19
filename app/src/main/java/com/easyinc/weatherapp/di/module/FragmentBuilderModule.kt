package com.easyinc.weatherapp.di.module

import com.easyinc.weatherapp.ui.ManageCityFragment
import com.easyinc.weatherapp.ui.WeatherAvgFragment
import com.easyinc.weatherapp.ui.cities.CitiesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideWeatherAvgFragment(): WeatherAvgFragment

    @ContributesAndroidInjector
    abstract fun provideMangeCityFragment(): ManageCityFragment

    @ContributesAndroidInjector
    abstract fun provideCitiesFragment(): CitiesFragment

}