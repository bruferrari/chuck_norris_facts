package com.bferrari.data.datasource

import com.bferrari.data.PastSearchCache
import com.bferrari.domain.PastSearch
import io.reactivex.Completable
import io.reactivex.Flowable

class PastSearchRepository(private val cache: PastSearchCache) : PastSearchDataSource {

    override fun getPastSearches(): Flowable<List<PastSearch>> = cache.get()

    override fun savePastSearch(pastSearch: PastSearch): Completable = cache.put(pastSearch)
}