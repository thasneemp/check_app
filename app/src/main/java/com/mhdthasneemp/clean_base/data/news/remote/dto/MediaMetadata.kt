package com.mhdthasneemp.clean_base.data.news.remote.dto


import com.google.gson.annotations.SerializedName

data class MediaMetadata(
    @SerializedName("format")
    var format: String?,
    @SerializedName("height")
    var height: Int?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
)