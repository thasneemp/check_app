package com.mhdthasneemp.clean_base.domain.news.usecase

import com.mhdthasneemp.clean_base.domain.base.BaseResult
import com.mhdthasneemp.clean_base.domain.news.NewsRepository
import com.mhdthasneemp.clean_base.domain.news.entity.NewListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsListUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(): Flow<BaseResult<NewListEntity, String>> {
        return newsRepository.getAllNews()
    }
}