package com.bferrari.usecases

import com.bferrari.data.datasource.FactsDataSource
import com.bferrari.domain.Fact

class SaveFacts(private val factsDataSource: FactsDataSource) {

    operator fun invoke(facts: List<Fact>) = factsDataSource.saveFacts(facts)
}