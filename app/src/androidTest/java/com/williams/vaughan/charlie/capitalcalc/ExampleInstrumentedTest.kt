package com.williams.vaughan.charlie.capitalcalc

import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Given
        val appContext = InstrumentationRegistry.getTargetContext()

        // When

        // Then
        assertEquals("com.williams.vaughan.charlie.capitalcalc", appContext.packageName)
    }
}