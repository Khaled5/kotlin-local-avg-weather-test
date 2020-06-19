package com.easyinc.weatherapp.domain.usecase

import com.easyinc.weatherapp.model.StartCity
import com.easyinc.weatherapp.common.threading.PostExecutionThread
import com.easyinc.weatherapp.common.threading.ThreadExecutor
import com.easyinc.weatherapp.domain.IWeatherRepository
import com.easyinc.weatherapp.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCityToUpdateUseCase @Inject constructor(
    private val weatherRepository: IWeatherRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): SingleUseCase<StartCity, String>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Single<StartCity> {
        return weatherRepository.getCityToUpdate(params!!)
    }

}