package com.mhdthasneemp.check_24.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<DataBinding : ViewDataBinding> :
    Fragment() {

    private lateinit var mActivity: BaseActivity<*>
    private lateinit var mRootView: View
    private lateinit var mViewDataBinding: DataBinding

    abstract fun getViewModel(): ViewModel

    fun getBindingModel(): DataBinding {
        return mViewDataBinding
    }

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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.lifecycleOwner = this
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
        mViewDataBinding.executePendingBindings()

    }

    override fun onStart() {
        super.onStart()
        observeOnStarted()
    }

    abstract fun observeOnStarted()

    fun showLoading(message: String = "Loading", show: Boolean) {
        (activity as? BaseActivity<*>)?.showProgressDialog(show = show)
    }


}