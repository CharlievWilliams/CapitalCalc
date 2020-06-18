package com.williams.vaughan.charlie.capitalcalc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.williams.vaughan.charlie.capitalcalc.extensions.LifeCycleAwareDisposable
import com.williams.vaughan.charlie.capitalcalc.viewstates.SplashViewEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.SplashViewEvent.ScreenLoadEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.SplashViewState
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val lifeCycleAwareDisposable: LifeCycleAwareDisposable
) : ViewModel(), LifeCycleAwareDisposable by lifeCycleAwareDisposable  {

    private val viewState: MutableLiveData<SplashViewState> = MutableLiveData()

    fun onEvent(event: SplashViewEvent) {
        when (event) {
            is ScreenLoadEvent -> onScreenLoad()
        }
    }

    private fun onScreenLoad() {

    }

    private fun onScreenLoadSuccess() {
        viewState.value = SplashViewState(
            isLoading = false
        )
    }

    fun viewState(): LiveData<SplashViewState> = viewState
}