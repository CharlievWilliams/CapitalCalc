package com.williams.vaughan.charlie.capitalcalc

import com.williams.vaughan.charlie.capitalcalc.fragments.CalculatorFragment
import com.williams.vaughan.charlie.capitalcalc.fragments.ResultFragment
import com.williams.vaughan.charlie.capitalcalc.fragments.SplashFragment
import dagger.Component

@Component(
    modules = [AppModule::class, PresentationModule::class]
)
interface ApplicationComponent {
    fun inject(fragment: SplashFragment)
    fun inject(fragment: CalculatorFragment)
    fun inject(fragment: ResultFragment)
}