package com.bferrari.usecases

import com.bferrari.data.datasource.FactsDataSource

class GetFacts(private val factsDataSource: FactsDataSource) {

    operator fun invoke(query: String) = factsDataSource.getFacts(query)
}