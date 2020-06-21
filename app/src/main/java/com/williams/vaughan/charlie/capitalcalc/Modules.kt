package com.williams.vaughan.charlie.capitalcalc

import com.williams.vaughan.charlie.capitalcalc.usecases.ConvertStringsToUsableUseCase
import com.williams.vaughan.charlie.capitalcalc.usecases.RetrieveTotalAmountUseCase
import com.williams.vaughan.charlie.capitalcalc.viewmodels.CalculatorViewModel
import com.williams.vaughan.charlie.capitalcalc.viewmodels.ResultViewModel
import com.williams.vaughan.charlie.capitalcalc.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ConvertStringsToUsableUseCase() }
    single { RetrieveTotalAmountUseCase(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { CalculatorViewModel(get()) }
    viewModel { ResultViewModel() }
}