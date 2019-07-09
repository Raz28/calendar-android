package com.gsv28rus.calendar.common.presentation

import androidx.lifecycle.ViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel constructor(protected val schedulerProvider: SchedulerProvider) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}