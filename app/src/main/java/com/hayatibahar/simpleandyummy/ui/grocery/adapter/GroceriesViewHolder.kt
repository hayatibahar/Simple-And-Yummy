package com.hayatibahar.simpleandyummy.ui.grocery.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.databinding.GroceriesItemBinding

class GroceriesViewHolder(
    private val binding: GroceriesItemBinding,
    private val onGroceryCheckBoxListener: ((Int, Boolean) -> Unit)?,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(groceryEntity: GroceryEntity) {
        with(binding) {
            itemNameTv.text = groceryEntity.nameClean
            "${groceryEntity.amount} ${groceryEntity.unit}".also { itemAmountTv.text = it }
            itemCb.isChecked = groceryEntity.isChecked
        }
        binding.itemCb.setOnClickListener {
            onGroceryCheckBoxListener?.invoke(groceryEntity.id, binding.itemCb.isChecked)
        }
    }
}