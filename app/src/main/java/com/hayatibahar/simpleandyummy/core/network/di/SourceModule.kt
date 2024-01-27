package com.hayatibahar.simpleandyummy.core.network.di

import com.hayatibahar.simpleandyummy.core.network.source.RemoteDataSource
import com.hayatibahar.simpleandyummy.core.network.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}