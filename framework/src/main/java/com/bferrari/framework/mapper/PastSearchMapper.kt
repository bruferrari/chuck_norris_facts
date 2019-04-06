package com.bferrari.framework.mapper

import com.bferrari.domain.PastSearch
import com.bferrari.framework.model.PastSearchEntity
import java.util.*

class PastSearchMapper : EntityMapper<PastSearchEntity, PastSearch> {

    override fun mapFrom(source: PastSearchEntity): PastSearch = PastSearch(source.query)

    override fun mapTo(source: PastSearch): PastSearchEntity = PastSearchEntity(source.query, Date())
}