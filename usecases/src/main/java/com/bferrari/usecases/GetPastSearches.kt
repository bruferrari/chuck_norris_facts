package com.bferrari.usecases

import com.bferrari.data.datasource.PastSearchDataSource

class GetPastSearches(private val pastSearchDataSource: PastSearchDataSource) {

    operator fun invoke() = pastSearchDataSource.getPastSearches()
}