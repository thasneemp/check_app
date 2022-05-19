package com.mhdthasneemp.check_24.data.products.remote.dto


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("headerDescription")
    var headerDescription: String?,
    @SerializedName("headerTitle")
    var headerTitle: String?
)