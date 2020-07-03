package com.williams.vaughan.charlie.capitalcalc

import com.robinhood.spark.SparkAdapter

class MySparkAdapter(private val yData: FloatArray) : SparkAdapter() {

    override fun getCount(): Int {
        return yData.size
    }

    override fun getItem(index: Int): Any {
        return yData[index]
    }

    override fun getY(index: Int): Float {
        return yData[index]
    }

}