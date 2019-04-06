package com.bferrari.usecases

import com.bferrari.data.datasource.CategoryDataSource

class SaveCategories(private val categoryDataSource: CategoryDataSource) {

    operator fun invoke() = categoryDataSource.saveCategories()
}