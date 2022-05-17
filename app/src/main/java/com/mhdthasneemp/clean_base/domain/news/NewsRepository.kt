package com.mhdthasneemp.clean_base.domain.news

import com.mhdthasneemp.clean_base.domain.base.BaseResult
import com.mhdthasneemp.clean_base.domain.news.entity.NewListEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getAllNews(): Flow<BaseResult<NewListEntity, String>>
}