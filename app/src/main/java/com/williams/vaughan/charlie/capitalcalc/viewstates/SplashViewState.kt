package com.williams.vaughan.charlie.capitalcalc.viewstates

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SplashViewState(
    val isLoading: Boolean = true
) : Parcelable

sealed class SplashViewEvent {
    object ScreenLoadEvent : SplashViewEvent()
    object SplashButtonPressedEvent : SplashViewEvent()
}

sealed class SplashNavigationEffect {
    object NavigateToCalculatorEffect : SplashNavigationEffect()
}