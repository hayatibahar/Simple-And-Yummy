package com.hayatibahar.simpleandyummy.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.common.inflateAdapterItem
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.databinding.IngredientsItemBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsViewHolder>() {

    private val items = mutableListOf<Ingredient>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(
            parent.inflateAdapterItem(IngredientsItemBinding::inflate),
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateIngredients(newItems: List<Ingredient>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}