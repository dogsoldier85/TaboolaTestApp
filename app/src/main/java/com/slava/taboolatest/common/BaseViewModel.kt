package com.slava.taboolatest.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposeOnClear = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeOnClear.clear()
    }

    protected fun Disposable.untilCleared() = disposeOnClear.add(this)
}