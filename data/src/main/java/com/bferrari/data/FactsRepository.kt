package com.bferrari.data

class FactsRepository(private val api: StoneAppApi) : FactsDataSource {

    override fun getFacts(query: String) = api.getFacts(query).map { it.result }
}