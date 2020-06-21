package com.williams.vaughan.charlie.capitalcalc.viewstates

data class CalculatorViewState(
    val isLoading: Boolean = true
)

sealed class CalculatorViewEvent {
    object ScreenLoadEvent : CalculatorViewEvent()
}

sealed class CalculatorNavigationEffect {
    object NavigateToResultEffect : CalculatorNavigationEffect()
}