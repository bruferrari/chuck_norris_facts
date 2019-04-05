package com.bferrari.framework

import com.bferrari.data.CategoryCache
import com.bferrari.domain.Category
import com.bferrari.framework.mapper.CategoryMapper
import io.reactivex.Completable
import io.reactivex.Flowable

class CategoryCacheImpl(private val db: AppDatabase,
                        private val mapper: CategoryMapper): CategoryCache {

    override fun save(categories: List<Category>): Completable {
        return db.categoryDao().insert(categories.map { mapper.mapToCached(it) })
    }

    override fun isCached() = db.query("SELECT id from categories", null).count > 0

    override fun get(): Flowable<List<Category>> {
        return db.categoryDao().read().map {
            it.map { categoryEntity -> mapper.mapFromCached(categoryEntity) }
        }
    }
}