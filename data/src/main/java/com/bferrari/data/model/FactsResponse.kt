package com.bferrari.data.model

import com.bferrari.domain.Facts

data class FactsResponse(val total: Int,
                         val result: List<Facts>)