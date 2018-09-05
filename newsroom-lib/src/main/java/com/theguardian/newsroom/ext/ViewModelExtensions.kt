package com.theguardian.newsroom.ext

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity

typealias NonNullObserver<T> = (T) -> Unit

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeNonNull(liveData: L, observer: NonNullObserver<T>) {
    liveData.observe(this, Observer {
        if (it != null) {
            observer.invoke(it)
        }
    })
}