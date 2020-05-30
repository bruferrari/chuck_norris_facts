package com.bferrari.data.datasource

import com.bferrari.data.AppApiService

class FactsRepository(private val api: AppApiService) : FactsDataSource {

    override fun getFacts(query: String) = api.getFacts(query).map { it.result }
}