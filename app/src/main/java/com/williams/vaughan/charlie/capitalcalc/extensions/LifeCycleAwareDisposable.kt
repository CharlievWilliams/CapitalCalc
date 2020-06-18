package com.williams.vaughan.charlie.capitalcalc.extensions

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

interface LifeCycleAwareDisposable {

    fun addDisposable(disposable: Disposable)

    fun dispose()

    class Default @Inject constructor() :
        LifeCycleAwareDisposable {

        private val disposables = CompositeDisposable()

        override fun addDisposable(disposable: Disposable) {
            disposables.add(disposable)
        }

        override fun dispose() {
            if (!disposables.isDisposed) {
                disposables.dispose()
            }
        }
    }
}