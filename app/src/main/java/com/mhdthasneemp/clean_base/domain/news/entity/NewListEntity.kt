package com.mhdthasneemp.clean_base.domain.news.entity

data class NewListEntity(
    var copyright: String?,
    var num_results: Int?,
    var results: List<ResultEntity>?,
    var status: String?
)