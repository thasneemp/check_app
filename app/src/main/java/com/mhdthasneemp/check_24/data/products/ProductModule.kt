package com.mhdthasneemp.check_24.data.products

import com.mhdthasneemp.check_24.data.common.module.LocalModule
import com.mhdthasneemp.check_24.data.common.module.NetworkModule
import com.mhdthasneemp.check_24.data.products.local.CheckDb
import com.mhdthasneemp.check_24.data.products.remote.api.ProductApi
import com.mhdthasneemp.check_24.data.products.repository.ProductRepositoryImpl
import com.mhdthasneemp.check_24.domain.prodocut.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, LocalModule::class])
@InstallIn(SingletonComponent::class)
object ProductModule {
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(loginApi: ProductApi, roomDatabase: CheckDb): ProductRepository {
        return ProductRepositoryImpl(loginApi,roomDatabase)
    }


}