package com.williams.vaughan.charlie.capitalcalc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.williams.vaughan.charlie.capitalcalc.extensions.Event
import com.williams.vaughan.charlie.capitalcalc.usecases.RetrieveTotalAmountUseCase
import com.williams.vaughan.charlie.capitalcalc.usecases.RetrieveTotalAmountUseCaseParams
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorNavigationEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEffect.ShowToastEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEvent.CalculateButtonPressed
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEvent.ScreenLoadEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewState

class CalculatorViewModel(
    private val retrieveTotalAmountUseCase: RetrieveTotalAmountUseCase
) : ViewModel() {

    private val viewState: MutableLiveData<CalculatorViewState> = MutableLiveData()
    private val viewEffect = MutableLiveData<Event<CalculatorViewEffect>>()
    private val navigationEffect = MutableLiveData<Event<CalculatorNavigationEffect>>()

    fun onEvent(event: CalculatorViewEvent) {
        when (event) {
            is ScreenLoadEvent -> onScreenLoad()
            is CalculateButtonPressed -> with(event) {
                onCalculateButtonPressed(
                    principalAmount,
                    annualInterestRate,
                    calculationPeriod,
                    compoundInterval,
                    monthlyDeposits,
                    depositAmount
                )
            }
        }
    }

    private fun onScreenLoad() {
        viewState.value = CalculatorViewState(
            principalAmount = "",
            annualInterestRate = "",
            calculationPeriod = "",
            compoundInterval = 2,
            monthlyDeposits = false,
            depositAmount = ""
        )
    }

    private fun onCalculateButtonPressed(
        principalAmount: String,
        annualInterestRate: String,
        calculationPeriod: String,
        compoundInterval: Int,
        monthlyDeposits: Boolean,
        depositAmount: String
    ) {
        viewState.value = viewState.value?.copy(
            principalAmount = principalAmount,
            annualInterestRate = annualInterestRate,
            calculationPeriod = calculationPeriod,
            compoundInterval = compoundInterval,
            monthlyDeposits = monthlyDeposits,
            depositAmount = depositAmount
        )

        val results = runBlocking {
            retrieveTotalAmountUseCase.execute(
                RetrieveTotalAmountUseCaseParams(
                    principalAmount,
                    annualInterestRate,
                    calculationPeriod,
                    compoundInterval,
                    monthlyDeposits,
                    depositAmount
                )
            )
        }
        when (results.success) {
            true -> navigationEffect.value = Event(NavigateToResultEffect)
            false -> viewEffect.value = Event(ShowToastEffect)
        }
    }

    fun viewState(): LiveData<CalculatorViewState> = viewState
    fun getViewEffect(): LiveData<Event<CalculatorViewEffect>> = viewEffect
    fun getNavigationEffect(): LiveData<Event<CalculatorNavigationEffect>> = navigationEffect
}