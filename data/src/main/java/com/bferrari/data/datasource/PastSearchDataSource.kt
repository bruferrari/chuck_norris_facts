package com.bferrari.data.datasource

import com.bferrari.domain.PastSearch
import io.reactivex.Completable
import io.reactivex.Flowable

interface PastSearchDataSource {

    fun getPastSearches(): Flowable<List<PastSearch>>

    fun savePastSearch(pastSearch: PastSearch): Completable
}