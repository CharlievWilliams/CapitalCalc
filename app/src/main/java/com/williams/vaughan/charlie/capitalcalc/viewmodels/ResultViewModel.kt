package com.williams.vaughan.charlie.capitalcalc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent.ScreenLoadEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewState

class ResultViewModel : ViewModel() {

    private val viewState: MutableLiveData<ResultViewState> = MutableLiveData()

    fun onEvent(event: ResultViewEvent) {
        when (event) {
            is ScreenLoadEvent -> onScreenLoad()
        }
    }

    private fun onScreenLoad() {

    }

    private fun onScreenLoadSuccess() {
        viewState.value = ResultViewState(
            isLoading = false
        )
    }

    fun viewState(): LiveData<ResultViewState> = viewState
}