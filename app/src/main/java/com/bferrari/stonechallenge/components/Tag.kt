package com.bferrari.stonechallenge.components

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.design.chip.ChipGroup
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.databinding.CategoryLayoutBinding

class Tag(context: Context, attributeSet: AttributeSet) : ChipGroup(context, attributeSet) {

    private var tags = emptyList<String>()

    fun setData(tags: List<String>) {
        this.tags = tags
        removeAllViews()
        tags.forEach { category ->
            category.run {
                val binding: CategoryLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                        R.layout.category_layout,
                        null,
                        false)

                binding.category = category.toUpperCase()

                addView(binding.root)
            }
        }
    }
}