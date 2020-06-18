package com.williams.vaughan.charlie.capitalcalc

import com.williams.vaughan.charlie.capitalcalc.extensions.LifeCycleAwareDisposable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Provides
    fun lifeCycleAwareDisposable(default: LifeCycleAwareDisposable.Default): LifeCycleAwareDisposable =
        default
}