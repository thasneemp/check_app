package com.mhdthasneemp.check_24.data.common.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mhdthasneemp.check_24.data.products.local.CheckDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideCheckD(@ApplicationContext appContext: Context): CheckDb {
        return Room.databaseBuilder(
            appContext,
            CheckDb::class.java, "database-name"
        ).build()
    }
}