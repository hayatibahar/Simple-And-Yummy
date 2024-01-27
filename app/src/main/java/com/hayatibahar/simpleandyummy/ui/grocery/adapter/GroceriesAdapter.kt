package com.hayatibahar.simpleandyummy.ui.grocery.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.common.inflateAdapterItem
import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.databinding.GroceriesItemBinding

class GroceriesAdapter : RecyclerView.Adapter<GroceriesViewHolder>() {

    private val items = mutableListOf<GroceryEntity>()

    private var onGroceryCheckBoxListener: ((Int, Boolean) -> Unit)? = null

    fun onGroceryCheckBoxListener(onGroceryCheckBoxListener: ((Int, Boolean) -> Unit)?) {
        this.onGroceryCheckBoxListener = onGroceryCheckBoxListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceriesViewHolder {
        return GroceriesViewHolder(
            parent.inflateAdapterItem(GroceriesItemBinding::inflate),
            onGroceryCheckBoxListener
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: GroceriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGroceries(newItems: List<GroceryEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}