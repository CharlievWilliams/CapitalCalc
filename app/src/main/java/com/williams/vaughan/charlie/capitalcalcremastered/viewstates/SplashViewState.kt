package com.williams.vaughan.charlie.capitalcalcremastered.viewstates

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