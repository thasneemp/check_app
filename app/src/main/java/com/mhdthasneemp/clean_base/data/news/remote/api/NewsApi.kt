package com.mhdthasneemp.clean_base.data.news.remote.api

import com.mhdthasneemp.clean_base.data.news.remote.dto.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json?")
    suspend fun getAllNews(
        @Path("section") section: String,
        @Path("period") period: String,
        @Query("api-key") apiKey :String
    ): Response<NewsModel>
}