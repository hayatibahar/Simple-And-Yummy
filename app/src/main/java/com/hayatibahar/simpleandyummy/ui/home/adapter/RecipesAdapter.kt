package com.hayatibahar.simpleandyummy.ui.home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.common.inflateAdapterItem
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.databinding.RecipeItemBinding

class RecipesAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    private val items = mutableListOf<Recipe>()
    private var onRecipeItemClickListener: ((String) -> Unit)? = null

    fun setOnRecipeItemClickListener(onRecipeItemClickListener: ((String) -> Unit)?) {
        this.onRecipeItemClickListener = onRecipeItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            parent.inflateAdapterItem(RecipeItemBinding::inflate),
            onRecipeItemClickListener
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRecipes(newItems: List<Recipe>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun getItemIdAtPosition(position: Int): Int {
        return items[position].id
    }

}