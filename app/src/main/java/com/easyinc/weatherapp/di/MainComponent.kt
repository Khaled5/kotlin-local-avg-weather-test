package com.easyinc.weatherapp.di

import android.app.Application
import com.easyinc.weatherapp.WeatherApp
import com.easyinc.weatherapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    DataModule::class,
    FragmentBuilderModule::class,
    RepositoryModule::class,
    ThreadingModule::class])
interface MainComponent: AndroidInjector<WeatherApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent

    }

}