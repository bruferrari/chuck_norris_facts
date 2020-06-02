package com.bferrari.usecases.injection

import com.bferrari.usecases.*
import org.koin.dsl.module.module

val useCaseModules = module {
    single { GetFacts(get()) }

    single { GetCategories(get()) }

    single { SaveCategories(get()) }

    single { GetPastSearches(get()) }

    single { SavePastSearch(get()) }

    single { SaveFacts(get()) }
}