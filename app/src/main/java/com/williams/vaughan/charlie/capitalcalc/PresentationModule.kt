package com.williams.vaughan.charlie.capitalcalc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.williams.vaughan.charlie.capitalcalc.extensions.ViewModelFactory
import com.williams.vaughan.charlie.capitalcalc.viewmodels.CalculatorViewModel
import com.williams.vaughan.charlie.capitalcalc.viewmodels.ResultViewModel
import com.williams.vaughan.charlie.capitalcalc.viewmodels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindCalendarViewModel(calendarViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalculatorViewModel::class)
    abstract fun bindCalculatorViewModel(diaryViewModel: CalculatorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    abstract fun bindResultViewModel(searchViewModel: ResultViewModel): ViewModel
}