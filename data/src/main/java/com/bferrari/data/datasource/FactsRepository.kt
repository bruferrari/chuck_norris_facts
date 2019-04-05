package com.bferrari.data.datasource

import com.bferrari.data.StoneAppApi

class FactsRepository(private val api: StoneAppApi) : FactsDataSource {

    override fun getFacts(query: String) = api.getFacts(query).map { it.result }
}