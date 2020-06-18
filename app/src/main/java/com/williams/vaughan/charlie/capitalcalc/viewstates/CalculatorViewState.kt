package com.williams.vaughan.charlie.capitalcalc.viewstates

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CalculatorViewState(
    val isLoading: Boolean = true
) : Parcelable

sealed class CalculatorViewEvent {
    object ScreenLoadEvent : CalculatorViewEvent()
}

sealed class CalculatorNavigationEffect {
    object NavigateToResultEffect : CalculatorNavigationEffect()
}