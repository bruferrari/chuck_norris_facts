package com.bferrari.data.datasource

import com.bferrari.domain.Fact
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface FactsDataSource {
    fun getFacts(query: String): Observable<List<Fact>>

    fun saveFacts(facts: List<Fact>): Completable

    fun getSingleFact(): Flowable<Fact>
}