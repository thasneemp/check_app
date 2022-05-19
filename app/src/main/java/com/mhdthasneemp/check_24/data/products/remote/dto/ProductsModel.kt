package com.mhdthasneemp.check_24.data.products.remote.dto


import com.google.gson.annotations.SerializedName

data class ProductsModel(
    @SerializedName("filters")
    var filters: List<String>?,
    @SerializedName("header")
    var header: Header?,
    @SerializedName("products")
    var products: List<Product>?
)