package com.easyinc.weatherapp.di.module

import com.easyinc.weatherapp.common.threading.JobExecutor
import com.easyinc.weatherapp.common.threading.PostExecutionThread
import com.easyinc.weatherapp.common.threading.ThreadExecutor
import com.easyinc.weatherapp.common.threading.UiThread
import dagger.Binds
import dagger.Module

@Module
abstract class ThreadingModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

}