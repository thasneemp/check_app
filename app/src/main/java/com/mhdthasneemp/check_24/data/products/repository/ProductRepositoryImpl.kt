package com.mhdthasneemp.check_24.data.products.repository

import com.mhdthasneemp.check_24.data.products.local.CheckDb
import com.mhdthasneemp.check_24.data.products.local.FavItem
import com.mhdthasneemp.check_24.data.products.remote.api.ProductApi
import com.mhdthasneemp.check_24.data.products.remote.dto.ProductsModel
import com.mhdthasneemp.check_24.domain.base.BaseResult
import com.mhdthasneemp.check_24.domain.prodocut.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val roomDatabase: CheckDb
) :
    ProductRepository {
    override suspend fun getAllProducts(): Flow<BaseResult<ProductsModel, String>> {
        return flow {
            val response =
                productApi.getAllProducts()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    emit(
                        BaseResult.Success(body)
                    )
                else
                    emit(BaseResult.Error(response.errorBody()?.charStream().toString()))
            } else {
                emit(BaseResult.Error(response.errorBody()?.charStream().toString()))
            }
        }
    }

    override suspend fun insert(favItem: FavItem): Flow<Long> {
        return flow {
            val insertFav = roomDatabase.favDao().insertFav(favItem)
            emit(insertFav)
        }
    }

}