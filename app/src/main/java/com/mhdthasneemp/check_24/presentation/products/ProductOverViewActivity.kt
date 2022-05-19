package com.mhdthasneemp.check_24.presentation.products

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.mhdthasneemp.check_24.data.products.local.CheckDb
import com.mhdthasneemp.check_24.domain.base.BaseActivity
import com.mhdthasneemp.checktwentyfoure.BR
import com.mhdthasneemp.checktwentyfoure.R
import com.mhdthasneemp.checktwentyfoure.databinding.ProductoverviewactivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductOverViewActivity : BaseActivity<ProductoverviewactivityBinding>() {
    private val viewModel: ProductOverViewViewModel by viewModels()

    @Inject
    lateinit var db: CheckDb

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.productoverviewactivity
    }

    override fun getViewModel(): ViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(getBindingModel().toolbar)
        intent.extras?.apply {
            viewModel.productItem.set(this.getParcelable("product"))
            supportActionBar?.title = viewModel.productItem.get()?.name
        }

        lifecycleScope.launch {
            val isFavored = db.favDao().isFavored(viewModel.productItem.get()?.id ?: -1)
            viewModel.isFavorite.set(isFavored == 1)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)

    }
}