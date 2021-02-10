package com.williams.vaughan.charlie.capitalcalcremastered.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.williams.vaughan.charlie.capitalcalcremastered.extensions.Event
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.SplashNavigationEffect
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.SplashNavigationEffect.NavigateToCalculatorEffect
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.SplashViewEvent
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.SplashViewEvent.ScreenLoadEvent
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.SplashViewEvent.SplashButtonPressedEvent
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.SplashViewState

class SplashViewModel : ViewModel() {

    private val viewState: MutableLiveData<SplashViewState> = MutableLiveData()
    private val navigationEffect = MutableLiveData<Event<SplashNavigationEffect>>()

    fun onEvent(event: SplashViewEvent) {
        when (event) {
            is ScreenLoadEvent -> onScreenLoad()
            is SplashButtonPressedEvent -> navigationEffect.value =
                Event(NavigateToCalculatorEffect)
        }
    }

    private fun onScreenLoad() {
        onScreenLoadSuccess()
    }

    private fun onScreenLoadSuccess() {
        viewState.value = SplashViewState(
            isLoading = false
        )
    }

    fun viewState(): LiveData<SplashViewState> = viewState
    fun getNavigationEffect(): LiveData<Event<SplashNavigationEffect>> = navigationEffect
}