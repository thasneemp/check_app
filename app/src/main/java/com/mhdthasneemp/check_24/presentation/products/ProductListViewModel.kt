package com.mhdthasneemp.check_24.presentation.products

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.mhdthasneemp.check_24.data.products.remote.dto.Product
import com.mhdthasneemp.check_24.domain.base.BaseFragmentViewModel
import com.mhdthasneemp.check_24.domain.base.BaseResult
import com.mhdthasneemp.check_24.domain.base.LiveEvent
import com.mhdthasneemp.check_24.domain.prodocut.usecase.ProductListUseCase
import com.mhdthasneemp.checktwentyfoure.BR
import com.mhdthasneemp.checktwentyfoure.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.OnItemBind
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productListUseCase: ProductListUseCase) :
    BaseFragmentViewModel() {

    private val state = MutableStateFlow<ProductListFragmentState>(ProductListFragmentState.Init)
    val mState: StateFlow<ProductListFragmentState> get() = state

    val productClick = LiveEvent<Product>()

    val mainTitle = ObservableField<String>()
    val subTitle = ObservableField<String>()
    val items = ObservableArrayList<Product>()
    val itemBind = OnItemBind<Product> { itemBind, pos, items ->
        val itemBinding = if (items.imageURL.isNullOrEmpty()) itemBind.set(
            BR.item,
            R.layout.product_list_items_right
        ) else itemBind.set(BR.item, R.layout.product_list_items)

        itemBinding.bindExtra(BR.clickListener, object : OnProductItemClickListener {
            override fun onProductClicked(product: Product) {
                productClick.value = product
            }

        })


    }

    fun loadAllProducts() {
        viewModelScope.launch {
            productListUseCase.getAllProducts().onStart {
                state.value = ProductListFragmentState.IsLoading(true)
            }.catch {
                state.value = ProductListFragmentState.IsLoading(false)
            }.collect { result ->
                state.value = ProductListFragmentState.IsLoading(false)
                when (result) {
                    is BaseResult.Success -> {
                        items.clear()
                        mainTitle.set(result.data.header?.headerTitle ?: "")
                        subTitle.set(result.data.header?.headerDescription ?: "")
                        items.addAll(result.data.products ?: emptyList())
                    }
                    else -> {

                    }
                }

            }
        }
    }
}

interface OnProductItemClickListener {
    fun onProductClicked(product: Product)
}

sealed class ProductListFragmentState {
    object Init : ProductListFragmentState()
    data class IsLoading(val isLoading: Boolean) : ProductListFragmentState()
    data class ShowToast(val message: String) : ProductListFragmentState()
}