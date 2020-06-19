package com.easyinc.weatherapp.domain.usecase

import com.easyinc.weatherapp.common.threading.PostExecutionThread
import com.easyinc.weatherapp.common.threading.ThreadExecutor
import com.easyinc.weatherapp.domain.IWeatherRepository
import com.easyinc.weatherapp.domain.usecase.base.ObservableUseCase
import com.easyinc.weatherapp.domain.usecase.base.SingleUseCase
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetCitiesNamesUseCase @Inject constructor(
    private val weatherRepository: IWeatherRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): ObservableUseCase<List<String>, Unit>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit?): Observable<List<String>> {
        return weatherRepository.getCitiesName()
    }

}