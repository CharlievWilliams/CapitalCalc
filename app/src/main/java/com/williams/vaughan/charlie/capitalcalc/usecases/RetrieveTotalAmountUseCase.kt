package com.williams.vaughan.charlie.capitalcalc.usecases

import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.ANNUALLY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.BIANNUALLY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.DAILY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.MONTHLY
import com.williams.vaughan.charlie.capitalcalc.CompoundInterval.QUARTERLY
import java.math.BigDecimal
import java.math.MathContext

class RetrieveTotalAmountUseCase(
    private val convertStringsToUsableUseCase: ConvertStringsToUsableUseCase
) {

    fun execute(retrieveTotalAmountUseCaseParams: RetrieveTotalAmountUseCaseParams): UseCaseResults {
        val results = convertStringsToUsableUseCase.execute(retrieveTotalAmountUseCaseParams)
        with(results) {
            return if (principalAmount == null ||
                annualInterestRate == null ||
                calculationPeriod == null ||
                (depositAmount == null && monthlyDeposits)
            ) {
                UseCaseResults(false, "", "")
            } else if (monthlyDeposits) {
                performMonthlyDepositCalculation(results)
            } else {
                performNonMonthlyDepositCalculation(results)
            }
        }
    }

    // Compound interest formulae: https://www.thecalculatorsite.com/articles/finance/compound-interest-formula.php
    private fun performNonMonthlyDepositCalculation(results: UsableUseCaseParams): UseCaseResults {
        with(results) {
            val p = principalAmount
            val r = annualInterestRate
            val n = when (compoundInterval) {
                DAILY -> BigDecimal(365)
                MONTHLY -> BigDecimal(12)
                QUARTERLY -> BigDecimal(4)
                BIANNUALLY -> BigDecimal(2)
                ANNUALLY -> BigDecimal(1)
            }
            val t = calculationPeriod

            val brackets = BigDecimal(1).add(r?.divide(n, MathContext.DECIMAL128))
            val upper = t?.let { n.times(it) }
            val main = upper?.let { brackets.times(it) }
            val amount = main?.let { p?.times(it) }

            return UseCaseResults(true, amount.toString(), calculationPeriod.toString())
        }
    }

    private fun performMonthlyDepositCalculation(results: UsableUseCaseParams): UseCaseResults {
        return UseCaseResults(false, "", "")
    }
}

data class RetrieveTotalAmountUseCaseParams(
    val principalAmount: String,
    val annualInterestRate: String,
    val calculationPeriod: String,
    val compoundInterval: Int,
    val monthlyDeposits: Boolean,
    val depositAmount: String?
)

data class UseCaseResults(
    val success: Boolean,
    val totalAmount: String,
    val timePeriod: String
)