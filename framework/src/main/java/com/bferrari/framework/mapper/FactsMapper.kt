package com.bferrari.framework.mapper

import com.bferrari.domain.Fact
import com.bferrari.framework.model.FactEntity

class FactsMapper: EntityMapper<FactEntity, Fact> {
    override fun mapFrom(source: FactEntity): Fact = Fact(
        id = source.id,
        value = source.value ?: ""
    )

    override fun mapTo(source: Fact): FactEntity = FactEntity(source.id, source.value)
}