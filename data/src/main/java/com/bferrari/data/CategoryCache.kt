package com.bferrari.data

import com.bferrari.domain.Category
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoryCache {
    fun put(categories: List<Category>): Completable

    fun get(): Flowable<List<Category>>

    fun isCached(): Boolean
}