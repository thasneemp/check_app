package com.mhdthasneemp.check_24.presentation.products

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.mhdthasneemp.checktwentyfoure.BR
import com.mhdthasneemp.checktwentyfoure.R
import com.mhdthasneemp.checktwentyfoure.databinding.AppNavigationHostViewmodelBinding
import com.mhdthasneemp.check_24.domain.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

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