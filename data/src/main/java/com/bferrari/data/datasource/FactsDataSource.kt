package com.bferrari.data.datasource

import com.bferrari.domain.Fact
import io.reactivex.Observable

interface FactsDataSource {
    fun getFacts(query: String): Observable<List<Fact>>
}