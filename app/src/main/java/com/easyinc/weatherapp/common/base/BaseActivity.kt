package com.easyinc.weatherapp.common.base

import com.easyinc.tasking.common.viewmodel.ViewModelFactory
import com.easyinc.weatherapp.common.base.viewmodel.factory.getViewModel
import com.easyinc.weatherapp.common.extentions.androidLazy
import com.easyinc.weatherapp.presentation.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    val mainViewModel by androidLazy {
        getViewModel<MainViewModel>(viewModelFactory)
    }




}