package com.easyinc.weatherapp.presentation

import com.easyinc.weatherapp.model.StartCity
import com.easyinc.weatherapp.common.base.viewmodel.BaseViewModel
import com.easyinc.weatherapp.common.util.Resource
import com.easyinc.weatherapp.domain.usecase.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val addCityUseCase: AddCityUseCase,
    private val deleteCityUseCase: DeleteCityUseCase,
    private val updateCityUseCase: UpdateCityUseCase,
    private val getCityDetailsUseCase: GetCityDetailsUseCase,
    private val getCityToUpdateUseCase: GetCityToUpdateUseCase,
    private val getCitiesNamesUseCase: GetCitiesNamesUseCase
): BaseViewModel() {

    fun getCitiesName(){
        compositeDisposable.add(
            getCitiesNamesUseCase.execute().subscribe(
                {
                    citiesNames.value = Resource.Success(it)
                },{
                    message.value = it.message
                }
            )
        )
    }

    fun addCity(city: StartCity){
        compositeDisposable.add(
            addCityUseCase.execute(city).subscribe(
                {
                    message.value = SUCCESS_MESSAGE
                },{
                    message.value = it.message
                }
            )
        )
    }

    fun deleteCity(city: StartCity){
        compositeDisposable.add(
            deleteCityUseCase.execute(city).subscribe(
                {
                    message.value = SUCCESS_MESSAGE
                },{
                    message.value = it.message
                }
            )
        )
    }

    fun updateCity(city: StartCity){
        compositeDisposable.add(
            updateCityUseCase.execute(city).subscribe(
                {
                    message.value = SUCCESS_MESSAGE
                },{
                    message.value = it.message
                }
            )
        )
    }

    fun getCityDetails(cityName: String, seasonName: String){
        val orderCity = OrderCity(cityName,seasonName)
        compositeDisposable.add(
            getCityDetailsUseCase.execute(orderCity).subscribe(
                {
                    cityDetails.value = Resource.Success(it)
                },{
                    message.value = it.message
                }
            )
        )
    }

    fun getCityToUpdate(cityName: String){
        compositeDisposable.add(
            getCityToUpdateUseCase.execute(cityName).subscribe(
                {
                    cityToUpdate.value = Resource.Success(it)
                },{
                    message.value = it.message
                }
            )
        )
    }

}