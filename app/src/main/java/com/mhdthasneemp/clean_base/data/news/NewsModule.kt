package com.mhdthasneemp.clean_base.data.news

import com.mhdthasneemp.clean_base.data.common.module.NetworkModule
import com.mhdthasneemp.clean_base.data.news.remote.api.NewsApi
import com.mhdthasneemp.clean_base.data.news.repository.NewsRepositoryImpl
import com.mhdthasneemp.clean_base.domain.news.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(loginApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(loginApi)
    }
}