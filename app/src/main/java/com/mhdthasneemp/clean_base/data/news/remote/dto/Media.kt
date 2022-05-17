package com.mhdthasneemp.clean_base.data.news.remote.dto


import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("approved_for_syndication")
    var approvedForSyndication: Int?,
    @SerializedName("caption")
    var caption: String?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("media-metadata")
    var mediaMetadata: List<MediaMetadata>?,
    @SerializedName("subtype")
    var subtype: String?,
    @SerializedName("type")
    var type: String?
)