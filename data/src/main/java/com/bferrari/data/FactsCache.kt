package com.bferrari.data

import com.bferrari.domain.Fact
import io.reactivex.Completable
import io.reactivex.Flowable

interface FactsCache {
    fun put(facts: List<Fact>): Completable

    fun get(): Flowable<Fact>

    fun isCached(): Boolean
}