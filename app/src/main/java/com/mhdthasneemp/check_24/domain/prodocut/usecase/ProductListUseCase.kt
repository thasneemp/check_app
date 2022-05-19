package com.mhdthasneemp.check_24.domain.prodocut.usecase

import com.mhdthasneemp.check_24.data.products.local.FavItem
import com.mhdthasneemp.check_24.data.products.remote.dto.ProductsModel
import com.mhdthasneemp.check_24.domain.base.BaseResult
import com.mhdthasneemp.check_24.domain.prodocut.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun getAllProducts(): Flow<BaseResult<ProductsModel, String>> {
        return productRepository.getAllProducts()
    }

    suspend fun insert(favItem: FavItem): Flow<Long> {
        return productRepository.insert(favItem)
    }
}