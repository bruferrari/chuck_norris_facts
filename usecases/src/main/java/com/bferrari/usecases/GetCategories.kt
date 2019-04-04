package com.bferrari.usecases

import com.bferrari.data.datasource.CategoryDataSource

class GetCategories(private val categoryDataSource: CategoryDataSource) {

    operator fun invoke() = categoryDataSource.getCategories()
}