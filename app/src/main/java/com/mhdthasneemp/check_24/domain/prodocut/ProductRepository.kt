package com.mhdthasneemp.check_24.domain.prodocut

import com.mhdthasneemp.check_24.data.products.local.FavItem
import com.mhdthasneemp.check_24.data.products.remote.dto.ProductsModel
import com.mhdthasneemp.check_24.domain.base.BaseResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllProducts(): Flow<BaseResult<ProductsModel, String>>
    suspend fun insert(favItem: FavItem):Flow<Long>
}