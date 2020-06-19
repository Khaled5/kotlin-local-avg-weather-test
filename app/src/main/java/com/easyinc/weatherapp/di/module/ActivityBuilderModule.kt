package com.easyinc.weatherapp.di.module

import com.easyinc.weatherapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

}