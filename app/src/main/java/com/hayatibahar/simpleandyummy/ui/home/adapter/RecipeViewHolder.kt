package com.hayatibahar.simpleandyummy.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.common.loadFromUrlByGlide
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.databinding.RecipeItemBinding

class RecipeViewHolder(
    private val binding: RecipeItemBinding,
    private val onRecipeItemClickListener: ((String) -> Unit)?,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: Recipe) {
        with(binding) {
            recipeNameTv.text = recipe.title
            "Ready in ${recipe.readyInMinutes} min".also { readyInMinutesTv.text = it }
            recipeIv.loadFromUrlByGlide(recipe.image)
        }
        binding.root.setOnClickListener {
            onRecipeItemClickListener?.invoke(recipe.id.toString())
        }
    }
}