package com.williams.vaughan.charlie.capitalcalc.usecases

import com.williams.vaughan.charlie.capitalcalc.CompoundInterval
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.ANNUALLY
import java.math.BigDecimal

class ConvertStringsToUsableUseCase {

    fun execute(retrieveTotalAmountUseCaseParams: RetrieveTotalAmountUseCaseParams): UsableUseCaseParams {
        with(retrieveTotalAmountUseCaseParams) {
            val convertedPrincipalAmount = principalAmount.toBigDecimal()
            val convertedAnnualInterestRate = annualInterestRate.toBigDecimal()
            val convertedCalculationPeriod = calculationPeriod.toInt()
            val convertedCompoundInterval = ANNUALLY
            return UsableUseCaseParams(
                convertedPrincipalAmount,
                convertedAnnualInterestRate,
                convertedCalculationPeriod,
                convertedCompoundInterval
            )
        }
    }
}

data class UsableUseCaseParams(
    val principalAmount: BigDecimal,
    val annualInterestRate: BigDecimal,
    val calculationPeriod: Int,
    val compoundInterval: CompoundInterval
)