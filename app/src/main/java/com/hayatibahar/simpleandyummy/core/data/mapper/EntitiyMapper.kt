package com.hayatibahar.simpleandyummy.core.data.mapper

import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.database.entity.RecipeEntity
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe

fun List<Recipe>.toRecipeEntityList(): List<RecipeEntity> {
    return this.map {
        RecipeEntity(
            id = it.id,
            title = it.title,
            image = it.image,
            readyInMinutes = it.readyInMinutes,
            isFavorite = false
        )
    }
}

fun List<RecipeEntity>.toRecipeList(): List<Recipe> {
    return this.map {
        Recipe(
            id = it.id,
            title = it.title,
            image = it.image,
            readyInMinutes = it.readyInMinutes,
        )
    }
}

fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        id = id,
        title = title,
        image = image,
        readyInMinutes = readyInMinutes,
        isFavorite = false
    )
}

fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = id,
        title = title,
        image = image,
        readyInMinutes = readyInMinutes,
    )
}

fun List<Ingredient>.toGroceryEntityList(): List<GroceryEntity> {
    return this.map {
        GroceryEntity(
            nameClean = it.nameClean,
            amount = it.amount,
            unit = it.unit,
            isChecked = false,
        )
    }
}


