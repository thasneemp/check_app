package com.mhdthasneemp.check_24.domain.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

abstract class BaseActivityViewModel : ViewModel() {
    val isVisible = ObservableBoolean(false)
}