package com.hayatibahar.simpleandyummy.ui.filter.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.R
import com.hayatibahar.simpleandyummy.databinding.FilterItemBinding


class FilterViewHolder(
    private val binding: FilterItemBinding,
    private val onFilterItemClickListener: ((FilterItem) -> Unit)?,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(filterItem: FilterItem) {
        with(binding) {
            nameTv.text = filterItem.name
            if (filterItem.isSelected) {
                frameLayout.setBackgroundResource(R.drawable.filter_selected_background)
            } else {
                frameLayout.setBackgroundResource(R.drawable.filter_unselected_background)
            }
        }
        binding.root.setOnClickListener {
            onFilterItemClickListener?.invoke(filterItem)
        }
    }
}