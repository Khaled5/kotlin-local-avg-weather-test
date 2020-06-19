package com.easyinc.weatherapp.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.easyinc.weatherapp.presentation.MainViewModel
import com.easyinc.weatherapp.ui.MainActivity
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment() {

    lateinit var navController: NavController

    lateinit var mainActivity: MainActivity

    abstract fun layoutId(): Int

    lateinit var mViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(layoutId(), container, false)
    }
}