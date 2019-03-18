package com.bferrari.data

import com.bferrari.domain.Facts

class FactsRepository(private val dataSource: FactsDataSource) {

    fun getFacts(query: String) = dataSource.getFacts(query)
}

interface FactsDataSource {
    fun getFacts(query: String): List<Facts>
}