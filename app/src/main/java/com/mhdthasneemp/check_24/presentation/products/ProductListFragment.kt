package com.mhdthasneemp.check_24.presentation.products

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mhdthasneemp.check_24.domain.base.BaseFragment
import com.mhdthasneemp.check_24.infra.utils.observe
import com.mhdthasneemp.checktwentyfoure.BR
import com.mhdthasneemp.checktwentyfoure.R
import com.mhdthasneemp.checktwentyfoure.databinding.ProductListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProductListFragment : BaseFragment<ProductListFragmentBinding>() {

    private var productFlowJob: Job? = null
    private val viewModel: ProductListViewModel by viewModels()
    override fun getViewModel(): ViewModel {
        return viewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.product_list_fragment
    }

    override fun observeOnStarted() {
        productFlowJob = viewModel.mState.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
            .onEach { state ->
                when (state) {
                    is ProductListFragmentState.Init -> {
                        Unit
                    }
                    is ProductListFragmentState.IsLoading -> {
                        getBindingModel().swiperefresh.isRefreshing = state.isLoading
                    }
                    else -> {}
                }

            }.launchIn(viewLifecycleOwner.lifecycleScope)

        observe(viewModel.productClick) { product ->

            val bundle = bundleOf("product" to product)
            findNavController().navigate(
                R.id.action_productListFragment_to_productOverViewActivity,
                bundle
            )

        }

        getBindingModel().swiperefresh.setOnRefreshListener {
            viewModel.loadAllProducts()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Call List Api
         */
        viewModel.loadAllProducts()
    }

    override fun onPause() {
        super.onPause()
        viewModel.productClick.removeObservers(this)
    }
}