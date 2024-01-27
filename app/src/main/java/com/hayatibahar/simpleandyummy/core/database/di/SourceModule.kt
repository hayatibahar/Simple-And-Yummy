package com.hayatibahar.simpleandyummy.core.database.di

import com.hayatibahar.simpleandyummy.core.database.source.LocalDataSource
import com.hayatibahar.simpleandyummy.core.database.source.LocalDataSourceImpl
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
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}