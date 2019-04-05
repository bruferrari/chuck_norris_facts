package com.bferrari.framework.mapper

interface EntityMapper<T, V> {

    fun mapFrom(source: T): V

    fun mapTo(source: V): T

}