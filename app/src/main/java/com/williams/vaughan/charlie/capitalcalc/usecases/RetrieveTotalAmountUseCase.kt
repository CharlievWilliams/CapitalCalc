package com.williams.vaughan.charlie.capitalcalc.usecases

class RetrieveTotalAmountUseCase(
    private val convertStringsToUsableUseCase: ConvertStringsToUsableUseCase
) {

    fun execute(retrieveTotalAmountUseCaseParams: RetrieveTotalAmountUseCaseParams) {

    }
}

data class RetrieveTotalAmountUseCaseParams(
    private val principalAmount: String,
    private val annualInterestRate: String,
    private val calculationPeriod: String,
    private val compoundInterval: Int,
    private val monthlyDeposits: Boolean,
    private val depositAmount: String?
)