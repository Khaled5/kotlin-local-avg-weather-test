package com.easyinc.weatherapp.domain.usecase.base

import com.easyinc.weatherapp.common.threading.PostExecutionThread
import com.easyinc.weatherapp.common.threading.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {


    abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(params: Params? = null): Observable<T> {
        return this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }
}