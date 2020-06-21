package com.williams.vaughan.charlie.capitalcalc.viewstates

data class CalculatorViewState(
    private val principalAmount: String,
    private val annualInterestRate: String,
    private val calculationPeriod: String,
    private val compoundInterval: Int,
    private val monthlyDeposits: Boolean,
    private val depositAmount: String?
)

sealed class CalculatorViewEvent {
    object ScreenLoadEvent : CalculatorViewEvent()
    data class CalculateButtonPressed(
        val principalAmount: String,
        val annualInterestRate: String,
        val calculationPeriod: String,
        val compoundInterval: Int,
        val monthlyDeposits: Boolean,
        val depositAmount: String
    ) : CalculatorViewEvent()
}

sealed class CalculatorViewEffect {
    object ShowToastEffect : CalculatorViewEffect()
}

sealed class CalculatorNavigationEffect {
    object NavigateToResultEffect : CalculatorNavigationEffect()
}