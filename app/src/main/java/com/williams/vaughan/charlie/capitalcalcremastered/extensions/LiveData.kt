package com.williams.vaughan.charlie.capitalcalcremastered.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<Event<T>>.observeEvent(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            observer.onChanged(it)
        }
    })
}