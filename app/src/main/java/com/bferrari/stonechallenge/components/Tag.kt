package com.bferrari.stonechallenge.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.databinding.CategoryLayoutBinding
import com.google.android.material.chip.ChipGroup

class Tag(context: Context, attributeSet: AttributeSet) : ChipGroup(context, attributeSet) {

    private var tags = emptyList<String>()

    fun setData(tags: List<String>, listener: ((String) -> Unit)?) {
        this.tags = tags
        removeAllViews()
        tags.forEach { category ->
            category.run {
                val binding: CategoryLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                        R.layout.category_layout,
                        null,
                        false)

                binding.category = category.toUpperCase()
                binding.factCategory.setOnClickListener { listener?.invoke(category) }

                addView(binding.root)
            }
        }
    }
}