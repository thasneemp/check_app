package com.mhdthasneemp.check_24.infra.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

internal fun <T> LifecycleOwner.observe(data: LiveData<T>?, block: (T) -> Unit) {
    data?.observe(this) {
        block(it)
    }
}