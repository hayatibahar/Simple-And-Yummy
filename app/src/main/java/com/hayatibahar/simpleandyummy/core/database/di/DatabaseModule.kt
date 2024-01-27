package com.hayatibahar.simpleandyummy.core.database.di

import android.content.Context
import androidx.room.Room
import com.hayatibahar.simpleandyummy.core.common.DbConstants.DATABASE_NAME
import com.hayatibahar.simpleandyummy.core.database.source.DataStoreManager
import com.hayatibahar.simpleandyummy.core.database.source.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): RecipeDatabase = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRecipeDao(database: RecipeDatabase) = database.recipeDao()

    @Provides
    @Singleton
    fun provideGroceryDao(database: RecipeDatabase) = database.groceryDao()

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}