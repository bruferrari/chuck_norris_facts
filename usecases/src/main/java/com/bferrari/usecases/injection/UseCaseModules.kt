package com.bferrari.usecases.injection

import com.bferrari.usecases.GetCategories
import com.bferrari.usecases.GetFacts
import com.bferrari.usecases.SaveCategories
import org.koin.dsl.module.module

val useCaseModules = module {
    single { GetFacts(get()) }

    single { GetCategories(get()) }

    single { SaveCategories(get()) }
}