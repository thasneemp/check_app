package com.mhdthasneemp.check_24.presentation.products

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.mhdthasneemp.check_24.data.products.local.FavItem
import com.mhdthasneemp.check_24.data.products.remote.dto.Product
import com.mhdthasneemp.check_24.domain.base.BaseActivityViewModel
import com.mhdthasneemp.check_24.domain.prodocut.usecase.ProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductOverViewViewModel @Inject constructor(private val productListUseCase: ProductListUseCase) :
    BaseActivityViewModel() {
    val productItem = ObservableField<Product>()
    val isFavorite = ObservableBoolean(false)

    fun makeFav(product: Product) {
        isFavorite.set(isFavorite.get().not())
        viewModelScope.launch {
            productListUseCase.insert(FavItem(product.id ?: 0, if (isFavorite.get()) 1 else 0))
        }
    }
}