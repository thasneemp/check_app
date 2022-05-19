package com.mhdthasneemp.check_24.data.products.remote.api

import com.mhdthasneemp.check_24.data.products.remote.dto.ProductsModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products-test.json")
    suspend fun getAllProducts(): Response<ProductsModel>
}