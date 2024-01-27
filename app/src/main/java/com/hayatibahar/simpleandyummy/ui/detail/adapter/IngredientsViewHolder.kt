package com.hayatibahar.simpleandyummy.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.BuildConfig
import com.hayatibahar.simpleandyummy.core.common.loadFromUrlByGlide
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.databinding.IngredientsItemBinding

class IngredientsViewHolder(
    private val binding: IngredientsItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ingredient: Ingredient) {
        with(binding) {
            ingredientTv.text = ingredient.originalName
            "${ingredient.amount} ${ingredient.unit}".also { amountTv.text = it }
            ingredientIv.loadFromUrlByGlide(BuildConfig.BASE_IMAGE_URL + ingredient.image)
        }
    }
}