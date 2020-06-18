package com.williams.vaughan.charlie.capitalcalc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.williams.vaughan.charlie.capitalcalc.extensions.LifeCycleAwareDisposable
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEvent.ScreenLoadEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewState
import javax.inject.Inject

class CalculatorViewModel @Inject constructor(
    private val lifeCycleAwareDisposable: LifeCycleAwareDisposable
) : ViewModel(), LifeCycleAwareDisposable by lifeCycleAwareDisposable  {

    private val viewState: MutableLiveData<CalculatorViewState> = MutableLiveData()

    fun onEvent(event: CalculatorViewEvent) {
        when (event) {
            is ScreenLoadEvent -> onScreenLoad()
        }
    }

    private fun onScreenLoad() {

    }

    private fun onScreenLoadSuccess() {
        viewState.value = CalculatorViewState(
            isLoading = false
        )
    }

    fun viewState(): LiveData<CalculatorViewState> = viewState
}