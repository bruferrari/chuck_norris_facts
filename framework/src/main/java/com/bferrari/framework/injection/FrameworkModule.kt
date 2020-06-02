package com.bferrari.framework.injection

import androidx.room.Room
import com.bferrari.data.CategoryCache
import com.bferrari.data.FactsCache
import com.bferrari.data.PastSearchCache
import com.bferrari.framework.AppDatabase
import com.bferrari.framework.CategoryCacheImpl
import com.bferrari.framework.FactsCacheImpl
import com.bferrari.framework.PastSearchCacheImpl
import com.bferrari.framework.mapper.CategoryMapper
import com.bferrari.framework.mapper.FactsMapper
import com.bferrari.framework.mapper.PastSearchMapper
import org.koin.dsl.module.module

val frameworkModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "stone_challenge").build()
    }

    single { CategoryMapper() }

    single { PastSearchMapper() }

    single { FactsMapper() }

    single<CategoryCache> { CategoryCacheImpl(get(), get()) }

    single<PastSearchCache> { PastSearchCacheImpl(get(), get()) }

    single<FactsCache> { FactsCacheImpl(get(), get()) }
}