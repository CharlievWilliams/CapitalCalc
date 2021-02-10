package com.williams.vaughan.charlie.capitalcalcremastered

import com.williams.vaughan.charlie.capitalcalcremastered.usecases.ConvertStringsToUsableUseCase
import com.williams.vaughan.charlie.capitalcalcremastered.usecases.RetrieveTotalAmountUseCase
import com.williams.vaughan.charlie.capitalcalcremastered.viewmodels.CalculatorViewModel
import com.williams.vaughan.charlie.capitalcalcremastered.viewmodels.ResultViewModel
import com.williams.vaughan.charlie.capitalcalcremastered.viewmodels.SplashViewModel
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