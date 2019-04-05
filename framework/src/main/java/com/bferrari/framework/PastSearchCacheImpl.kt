package com.bferrari.framework

import com.bferrari.data.PastSearchCache
import com.bferrari.domain.PastSearch
import com.bferrari.framework.mapper.PastSearchMapper
import io.reactivex.Completable
import io.reactivex.Flowable

class PastSearchCacheImpl(private val db: AppDatabase,
                          private val mapper: PastSearchMapper) : PastSearchCache {

    override fun put(pastSearch: PastSearch): Completable {
        return db.pastSearchDao().insert(mapper.mapTo(pastSearch))
    }

    override fun get(): Flowable<List<PastSearch>> {
        return db.pastSearchDao().read().map {
            it.map { pastSearch -> mapper.mapFrom(pastSearch) }
        }
    }

    override fun isCached(): Boolean = db.query("SELECT id FROM past_searches", null).count > 0
}