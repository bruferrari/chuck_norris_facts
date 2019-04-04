package com.bferrari.data

import com.bferrari.domain.Category
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoryCache {
    fun save(categories: List<Category>): Completable
    fun isCached(): Boolean
    fun get(): Flowable<List<Category>>
}