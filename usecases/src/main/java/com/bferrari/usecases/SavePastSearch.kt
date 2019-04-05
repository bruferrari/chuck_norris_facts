package com.bferrari.usecases

import com.bferrari.data.datasource.PastSearchDataSource
import com.bferrari.domain.PastSearch

class SavePastSearch(private val pastSearchDataSource: PastSearchDataSource) {

    operator fun invoke(pastSearch: PastSearch) = pastSearchDataSource.savePastSearch(pastSearch)
}