package com.bferrari.framework.mapper

import com.bferrari.domain.Category
import com.bferrari.framework.model.CategoryEntity

class CategoryMapper: EntityMapper<CategoryEntity, Category> {
    override fun mapFromCached(source: CategoryEntity): Category = Category(source.id, source.name)

    override fun mapToCached(source: Category): CategoryEntity = CategoryEntity(source.id, source.name)
}