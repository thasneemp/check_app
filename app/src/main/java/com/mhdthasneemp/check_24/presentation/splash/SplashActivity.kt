package com.mhdthasneemp.check_24.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.mhdthasneemp.check_24.domain.base.BaseActivity
import com.mhdthasneemp.check_24.presentation.products.AppNavigationHostActivity
import com.mhdthasneemp.checktwentyfoure.BR
import com.mhdthasneemp.checktwentyfoure.R
import com.mhdthasneemp.checktwentyfoure.databinding.SplashActivityBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<SplashActivityBinding>() {

    private val viewModel: SplashViewModel by viewModels()
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.splash_activity
    }

    override fun getViewModel(): ViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, AppNavigationHostActivity::class.java))
            finish()
        }
    }
}