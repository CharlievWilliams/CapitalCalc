package com.williams.vaughan.charlie.capitalcalc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.williams.vaughan.charlie.capitalcalc.extensions.Event
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultNavigationEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultNavigationEffect.NavigateToCalculatorEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent.ReturnPressedEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent.ScreenLoadEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewState

class ResultViewModel : ViewModel() {

    private val viewState: MutableLiveData<ResultViewState> = MutableLiveData()
    private val navigationEffect = MutableLiveData<Event<ResultNavigationEffect>>()

    fun onEvent(event: ResultViewEvent) {
        when (event) {
            is ScreenLoadEvent -> onScreenLoad()
            is ReturnPressedEvent -> onReturnPressed()
        }
    }

    private fun onScreenLoad() {
        onScreenLoadSuccess()
    }

    private fun onScreenLoadSuccess() {
        viewState.value = ResultViewState(
            isLoading = false
        )
    }

    private fun onReturnPressed() {
        navigationEffect.value = Event(NavigateToCalculatorEffect)
    }

    fun viewState(): LiveData<ResultViewState> = viewState
    fun getNavigationEffect(): LiveData<Event<ResultNavigationEffect>> = navigationEffect
}