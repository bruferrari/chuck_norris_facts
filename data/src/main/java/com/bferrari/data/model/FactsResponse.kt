package com.bferrari.data.model

import com.bferrari.domain.Fact

data class FactsResponse(val total: Int,
                         val result: List<Fact>)