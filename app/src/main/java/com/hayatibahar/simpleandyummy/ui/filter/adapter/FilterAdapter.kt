package com.hayatibahar.simpleandyummy.ui.filter.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.common.inflateAdapterItem
import com.hayatibahar.simpleandyummy.databinding.FilterItemBinding

class FilterAdapter : RecyclerView.Adapter<FilterViewHolder>() {

    private val items = mutableListOf<FilterItem>()

    private var onFilterItemClickListener: ((FilterItem) -> Unit)? = null

    fun setOnFilterItemClickListener(onFilterItemClickListener: ((FilterItem) -> Unit)?) {
        this.onFilterItemClickListener = onFilterItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            parent.inflateAdapterItem(FilterItemBinding::inflate),
            onFilterItemClickListener
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFilters(newItems: List<FilterItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}