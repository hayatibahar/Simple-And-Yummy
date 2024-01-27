package com.hayatibahar.simpleandyummy.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.common.inflateAdapterItem
import com.hayatibahar.simpleandyummy.core.domain.model.Instruction
import com.hayatibahar.simpleandyummy.databinding.InstructionItemBinding

class InstructionAdapter : RecyclerView.Adapter<InstructionViewHolder>() {

    private val items = mutableListOf<Instruction>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        return InstructionViewHolder(
            parent.inflateAdapterItem(InstructionItemBinding::inflate),
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateInstructions(newItems: List<Instruction>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}