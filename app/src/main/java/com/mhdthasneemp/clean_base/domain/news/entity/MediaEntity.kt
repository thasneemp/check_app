package com.mhdthasneemp.clean_base.domain.news.entity

data class MediaEntity(
    var approved_for_syndication: Int?,
    var caption: String?,
    var copyright: String?,
    var mediaMetadata: List<MediaMetadataEntity>?,
    var subtype: String?,
    var type: String?
)