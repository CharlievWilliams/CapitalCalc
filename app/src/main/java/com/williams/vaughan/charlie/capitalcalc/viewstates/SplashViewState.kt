package com.williams.vaughan.charlie.capitalcalc.viewstates

data class SplashViewState(
    val isLoading: Boolean = true
)

sealed class SplashViewEvent {
    object ScreenLoadEvent : SplashViewEvent()
    object SplashButtonPressedEvent : SplashViewEvent()
}

sealed class SplashNavigationEffect {
    object NavigateToCalculatorEffect : SplashNavigationEffect()
}