package com.williams.vaughan.charlie.capitalcalc.usecases

import com.williams.vaughan.charlie.capitalcalc.CompoundInterval

class ConvertStringsToUsableUseCase {

    fun execute() {

    }
}

data class ConvertStringsToUsableUseCaseParams(
    private val principalAmount: String,
    private val annualInterestRate: String,
    private val calculationPeriod: String,
    private val compoundInterval: CompoundInterval,
    private val monthlyDeposits: Boolean,
    private val depositAmount: String?
)