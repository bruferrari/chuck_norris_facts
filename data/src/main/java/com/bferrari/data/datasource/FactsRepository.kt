package com.bferrari.data.datasource

import com.bferrari.data.AppApiService
import com.bferrari.data.FactsCache
import com.bferrari.domain.Fact
import io.reactivex.Completable
import io.reactivex.Flowable

class FactsRepository(private val api: AppApiService,
                      private val cache: FactsCache) : FactsDataSource {

    override fun getFacts(query: String) =
        api.getFacts(query).map { it.result }
            .doOnNext { saveFacts(it).subscribe() }

    override fun saveFacts(facts: List<Fact>): Completable {
        //TODO: clear table first?
        return cache.put(facts)
    }

    override fun getSingleFact(): Flowable<Fact> = cache.get()
}