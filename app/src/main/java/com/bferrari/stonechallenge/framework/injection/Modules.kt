package com.bferrari.stonechallenge.framework.injection

import com.bferrari.stonechallenge.ui.factslist.FactsViewModel
import org.koin.dsl.module.module

val appModule = module {
    factory { FactsViewModel(get()) }
}
