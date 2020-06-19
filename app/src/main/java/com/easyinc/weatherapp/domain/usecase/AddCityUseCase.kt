package com.easyinc.weatherapp.domain.usecase

import com.easyinc.weatherapp.model.StartCity
import com.easyinc.weatherapp.common.threading.PostExecutionThread
import com.easyinc.weatherapp.common.threading.ThreadExecutor
import com.easyinc.weatherapp.domain.IWeatherRepository
import com.easyinc.weatherapp.domain.usecase.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class AddCityUseCase @Inject constructor(
    private val weatherRepository: IWeatherRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): CompletableUseCase<StartCity>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseObservable(params: StartCity?): Completable {
        return weatherRepository.addCity(params!!)
    }

}