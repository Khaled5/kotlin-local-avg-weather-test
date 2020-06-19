package com.easyinc.weatherapp.domain.usecase.base

import com.easyinc.weatherapp.common.threading.PostExecutionThread
import com.easyinc.weatherapp.common.threading.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    abstract fun buildUseCaseObservable(params: Params? = null) : Single<T>

    open fun execute(params: Params? = null): Single<T> {
        return this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }
}