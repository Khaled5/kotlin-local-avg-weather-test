package com.easyinc.weatherapp.domain.usecase

import com.easyinc.weatherapp.model.FinalCity
import com.easyinc.weatherapp.common.threading.PostExecutionThread
import com.easyinc.weatherapp.common.threading.ThreadExecutor
import com.easyinc.weatherapp.domain.IWeatherRepository
import com.easyinc.weatherapp.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCityDetailsUseCase @Inject constructor(
    private val weatherRepository: IWeatherRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): SingleUseCase<FinalCity, OrderCity>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseObservable(params: OrderCity?): Single<FinalCity> {
        return weatherRepository.getCityDetails(params!!.cityName,params.season)
    }


}

data class OrderCity(
    val cityName: String,
    val season: String
)