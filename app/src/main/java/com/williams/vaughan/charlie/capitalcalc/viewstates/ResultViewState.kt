package com.williams.vaughan.charlie.capitalcalc.viewstates

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultViewState(
    val isLoading: Boolean = true
) : Parcelable

sealed class ResultViewEvent {
    object ScreenLoadEvent : ResultViewEvent()
}

sealed class ResultNavigationEffect {
    object NavigateToCalculatorEffect : ResultNavigationEffect()
}