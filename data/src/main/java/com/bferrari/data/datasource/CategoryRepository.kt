package com.bferrari.data.datasource

import com.bferrari.data.CategoryCache
import com.bferrari.data.StoneAppApi
import com.bferrari.domain.Category
import io.reactivex.Completable
import io.reactivex.Flowable
import com.bferrari.data.RetryWithDelay


class CategoryRepository(private val api: StoneAppApi,
                         private val cache: CategoryCache): CategoryDataSource {

    companion object {
        const val FIRST_CALL = 4
        const val SECOND_CALL = 8
    }

    override fun saveCategories(): Completable {
        return if (!cache.isCached()) {
            api.getCategories()
                .retryWhen(RetryWithDelay(listOf(FIRST_CALL, SECOND_CALL)))
                .flatMapCompletable {
                    cache.save(it.mapIndexed { id, value ->
                        Category(id + 1, value)
                    })
                }
        } else Completable.complete()
    }

    override fun getCategories(): Flowable<List<Category>> = cache.get()
}