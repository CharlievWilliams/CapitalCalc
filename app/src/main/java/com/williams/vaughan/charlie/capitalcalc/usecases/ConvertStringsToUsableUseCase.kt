package com.williams.vaughan.charlie.capitalcalc.usecases

import com.williams.vaughan.charlie.capitalcalc.CompoundInterval
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.ANNUALLY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.BIANNUALLY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.DAILY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.MONTHLY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.QUARTERLY
import java.math.BigDecimal

class ConvertStringsToUsableUseCase {

    fun execute(retrieveTotalAmountUseCaseParams: RetrieveTotalAmountUseCaseParams): UsableUseCaseParams {
        with(retrieveTotalAmountUseCaseParams) {
            val convertedPrincipalAmount = principalAmount.toBigDecimalOrNull()
            val convertedAnnualInterestRate = annualInterestRate.toBigDecimalOrNull()
            val convertedCalculationPeriod = calculationPeriod.toBigDecimalOrNull()
            val convertedCompoundInterval = when (compoundInterval) {
                1 -> DAILY
                2 -> MONTHLY
                3 -> QUARTERLY
                4 -> BIANNUALLY
                5 -> ANNUALLY
                else -> MONTHLY
            }
            val convertedMonthlyDeposits = monthlyDeposits
            val convertedDepositAmount = depositAmount?.toBigDecimalOrNull()
            return UsableUseCaseParams(
                convertedPrincipalAmount,
                convertedAnnualInterestRate,
                convertedCalculationPeriod,
                convertedCompoundInterval,
                convertedMonthlyDeposits,
                convertedDepositAmount
            )
        }
    }
}

data class UsableUseCaseParams(
    val principalAmount: BigDecimal?,
    val annualInterestRate: BigDecimal?,
    val calculationPeriod: BigDecimal?,
    val compoundInterval: CompoundInterval,
    val monthlyDeposits: Boolean,
    val depositAmount: BigDecimal?
)