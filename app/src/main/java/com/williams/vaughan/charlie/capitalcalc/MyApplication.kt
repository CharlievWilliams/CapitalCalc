package com.williams.vaughan.charlie.capitalcalc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.builder().build()
}
