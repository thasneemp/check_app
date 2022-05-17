package com.mhdthasneemp.clean_base.data.news.repository

import com.mhdthasneemp.clean_base.data.news.remote.api.NewsApi
import com.mhdthasneemp.clean_base.domain.base.BaseResult
import com.mhdthasneemp.clean_base.domain.news.NewsRepository
import com.mhdthasneemp.clean_base.domain.news.entity.MediaEntity
import com.mhdthasneemp.clean_base.domain.news.entity.MediaMetadataEntity
import com.mhdthasneemp.clean_base.domain.news.entity.NewListEntity
import com.mhdthasneemp.clean_base.domain.news.entity.ResultEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {
    override suspend fun getAllNews(): Flow<BaseResult<NewListEntity, String>> {
        return flow {
            val response =
                newsApi.getAllNews("all-sections", "7", "Ld0AF7Vw0WXoTL5oGQSYPJGlb8PSrYrN")
            if (response.isSuccessful) {
                val body = response.body()
                emit(
                    BaseResult.Success(
                        NewListEntity(
                            body?.copyright,
                            body?.numResults,
                            body?.results?.map {
                                ResultEntity(
                                    it.abstract, it.adxKeywords, it.assetId,
                                    it.byline,
                                    it.column,
                                    it.desFacet?.map { item -> item },
                                    it.etaId,
                                    it.geoFacet?.map { item -> item },
                                    it.id,
                                    it.media?.map { media ->
                                        MediaEntity(
                                            media.approvedForSyndication,
                                            media.caption,
                                            media.copyright,
                                            media.mediaMetadata?.map { metaData ->
                                                MediaMetadataEntity(
                                                    metaData.format,
                                                    metaData.height,
                                                    metaData.url,
                                                    metaData.width
                                                )
                                            }, media.subtype, media.type
                                        )
                                    },
                                    it.nytdsection, it.orgFacet?.map { facet -> facet },
                                    it.perFacet?.map { perFacet -> perFacet },
                                    it.publishedDate,
                                    it.section,
                                    it.source,
                                    it.subsection,
                                    it.title,
                                    it.type,
                                    it.updated,
                                    it.uri,
                                    it.url
                                )
                            },
                            body?.status
                        )
                    )
                )
            } else {
                emit(BaseResult.Error(response.errorBody()?.charStream().toString()))
            }
        }
    }
}