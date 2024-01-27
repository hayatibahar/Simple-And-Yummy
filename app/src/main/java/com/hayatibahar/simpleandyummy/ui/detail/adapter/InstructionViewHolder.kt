package com.hayatibahar.simpleandyummy.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.domain.model.Instruction
import com.hayatibahar.simpleandyummy.databinding.InstructionItemBinding

class InstructionViewHolder(
    private val binding: InstructionItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(instruction: Instruction) {
        with(binding) {
            "Step ${instruction.number}".also { stepNumberTv.text = it }
            stepDescriptionTv.text = instruction.step
        }
    }
}