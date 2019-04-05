package com.bferrari.data

import com.bferrari.domain.PastSearch
import io.reactivex.Completable
import io.reactivex.Flowable

interface PastSearchCache {
    fun put(pastSearch: PastSearch): Completable

    fun get(): Flowable<List<PastSearch>>

    fun isCached(): Boolean
}