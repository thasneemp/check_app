package com.mhdthasneemp.clean_base.presentation.news

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.mhdthasneemp.clean_base.BR
import com.mhdthasneemp.clean_base.R
import com.mhdthasneemp.clean_base.databinding.AppNavigationHostViewmodelBinding
import com.mhdthasneemp.clean_base.domain.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppNavigationHostActivity :
    BaseActivity<AppNavigationHostViewmodelBinding>() {

    private val viewModel: AppNavigationHostViewModel by viewModels()


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.app_navigation_host_viewmodel
    }

    override fun getViewModel(): ViewModel {
        return viewModel
    }


}