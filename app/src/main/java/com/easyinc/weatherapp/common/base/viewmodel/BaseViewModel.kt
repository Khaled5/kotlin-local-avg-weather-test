package com.easyinc.weatherapp.common.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easyinc.weatherapp.model.FinalCity
import com.easyinc.weatherapp.model.StartCity
import com.easyinc.weatherapp.common.util.Resource
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val message by lazy { MutableLiveData<String>() }
    val cityDetails by lazy { MutableLiveData<Resource<FinalCity>>() }
    val cityToUpdate by lazy { MutableLiveData<Resource<StartCity>>() }
    val citiesNames by lazy { MutableLiveData<Resource<List<String>>>() }


    fun observeMessage(): LiveData<String> = message
    fun observeCityDetails(): LiveData<Resource<FinalCity>> = cityDetails
    fun observeCityToUpdate(): LiveData<Resource<StartCity>> = cityToUpdate
    fun observeCitiesNames(): LiveData<Resource<List<String>>> = citiesNames

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    companion object{
        const val SUCCESS_MESSAGE = "Succeed operation"
    }
}