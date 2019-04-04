package com.bferrari.framework.mapper

interface EntityMapper<T, V> {

    fun mapFromCached(source: T): V

    fun mapToCached(source: V): T

}