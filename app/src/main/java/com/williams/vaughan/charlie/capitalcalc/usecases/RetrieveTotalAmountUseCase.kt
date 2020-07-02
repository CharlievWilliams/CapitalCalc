package com.williams.vaughan.charlie.capitalcalc.usecases

import java.math.BigDecimal

class RetrieveTotalAmountUseCase(
    private val convertStringsToUsableUseCase: ConvertStringsToUsableUseCase
) {

    fun execute(retrieveTotalAmountUseCaseParams: RetrieveTotalAmountUseCaseParams): UseCaseResults {
        val results = convertStringsToUsableUseCase.execute(retrieveTotalAmountUseCaseParams)
        return performCalculation(results)
    }
}

private fun performCalculation(results: UsableUseCaseParams): UseCaseResults {
    val chartData: MutableList<Float> = ArrayList()
    var year = 0

    with(results) {
        var total = principalAmount
        val percent = (BigDecimal(100).add(annualInterestRate)).divide(BigDecimal(100))
        chartData.add(total.toFloat())
        while (year < calculationPeriod) {
            total = total.times(percent)
            chartData.add(total.toFloat())
            year++
        }

        return UseCaseResults(
            true,
            total.toString(),
            calculationPeriod.toString(),
            chartData.toFloatArray()
        )
    }
}

data class RetrieveTotalAmountUseCaseParams(
    val principalAmount: String,
    val annualInterestRate: String,
    val calculationPeriod: String,
    val compoundInterval: Int
)

data class UseCaseResults(
    val success: Boolean,
    val totalAmount: String,
    val timePeriod: String,
    val chartData: FloatArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UseCaseResults

        if (!chartData.contentEquals(other.chartData)) return false

        return true
    }

    override fun hashCode(): Int {
        return chartData.contentHashCode()
    }
}