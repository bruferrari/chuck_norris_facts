package com.bferrari.data.datasource

import com.bferrari.domain.Category
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoryDataSource {

    fun getCategories(): Flowable<List<Category>>

    fun saveCategories(): Completable
}