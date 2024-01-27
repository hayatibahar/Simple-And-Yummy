package com.hayatibahar.simpleandyummy.core.data.di

import com.hayatibahar.simpleandyummy.core.data.repository.RecipeRepositoryImpl
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RecipeRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(recipeRepositoryImpl: RecipeRepositoryImpl): RecipeRepository
}