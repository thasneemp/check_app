package com.mhdthasneemp.clean_base.domain.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


abstract class BaseActivity<DataBinding : ViewDataBinding> :
    AppCompatActivity() {

    lateinit var mViewDataBinding: DataBinding


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
        mViewDataBinding.executePendingBindings()
    }

    abstract fun getViewModel(): ViewModel

    fun hideProgress() {
        showProgressDialog(show = false)
    }

    fun showProgressDialog(message: String = "Loading", show: Boolean) {
        if (getViewModel() is BaseActivityViewModel) {
            (getViewModel() as BaseActivityViewModel).isVisible.set(show)
        }
    }

}