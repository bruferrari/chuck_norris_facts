package com.bferrari.framework.mapper

import com.bferrari.domain.PastSearch
import com.bferrari.framework.model.PastSearchEntity

class PastSearchMapper : EntityMapper<PastSearchEntity, PastSearch> {

    override fun mapFrom(source: PastSearchEntity): PastSearch = PastSearch(source.id, source.query, source.date)

    override fun mapTo(source: PastSearch): PastSearchEntity = PastSearchEntity(source.id, source.query, source.date)
}