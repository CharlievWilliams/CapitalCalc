package com.williams.vaughan.charlie.capitalcalcremastered.viewstates

data class ResultViewState(
    val isLoading: Boolean = true
)

sealed class ResultViewEvent {
    object ScreenLoadEvent : ResultViewEvent()
    object ReturnPressedEvent : ResultViewEvent()
}

sealed class ResultNavigationEffect {
    object NavigateToCalculatorEffect : ResultNavigationEffect()
}