package com.bferrari.framework.injection

import androidx.room.Room
import com.bferrari.data.CategoryCache
import com.bferrari.framework.AppDatabase
import com.bferrari.framework.CategoryCacheImpl
import com.bferrari.framework.mapper.CategoryMapper
import org.koin.dsl.module.module

val frameworkModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "stone_challenge").build()
    }

    single { CategoryMapper() }

    single<CategoryCache> { CategoryCacheImpl(get(), get()) }
}