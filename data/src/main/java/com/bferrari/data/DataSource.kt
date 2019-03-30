package com.bferrari.data

import com.bferrari.domain.Facts
import io.reactivex.Observable

interface FactsDataSource {
    fun getFacts(query: String): Observable<List<Facts>>
}