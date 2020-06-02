package com.bferrari.framework

import com.bferrari.data.FactsCache
import com.bferrari.domain.Fact
import com.bferrari.framework.mapper.FactsMapper
import io.reactivex.Completable
import io.reactivex.Flowable

class FactsCacheImpl(private val db: AppDatabase,
                     private val mapper: FactsMapper): FactsCache {

    override fun put(facts: List<Fact>): Completable = db.factsDao().insert(facts.map { mapper.mapTo(it) })

    override fun get(): Flowable<Fact> = db.factsDao().read().map { mapper.mapFrom(it) }

    override fun isCached(): Boolean = db.query("SELECT id FROM facts", null).count > 0
}