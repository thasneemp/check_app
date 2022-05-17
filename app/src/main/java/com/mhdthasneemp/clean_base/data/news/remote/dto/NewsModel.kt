package com.mhdthasneemp.clean_base.data.news.remote.dto


import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("results")
    var results: List<Result>?,
    @SerializedName("status")
    var status: String?
)