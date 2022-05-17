package com.mhdthasneemp.clean_base.domain.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

abstract class BaseActivityViewModel : ViewModel() {
    val isVisible = ObservableBoolean(false)
}