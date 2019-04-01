package com.bferrari.usecases.injection

import com.bferrari.usecases.GetFacts
import org.koin.dsl.module.module

val useCaseModules = module {
    single { GetFacts(get()) }
}