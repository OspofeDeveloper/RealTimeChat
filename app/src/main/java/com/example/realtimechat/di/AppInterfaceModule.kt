package com.example.realtimechat.di

import com.example.realtimechat.data.DataRepositoryImpl
import com.example.realtimechat.domain.DataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppInterfaceModule {

    @Binds
    @Singleton
    abstract fun provideDataRepository(
        dataRepositoryImpl: DataRepositoryImpl
    ): DataRepository
}