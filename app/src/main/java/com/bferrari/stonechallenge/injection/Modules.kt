package com.bferrari.stonechallenge.injection

import com.bferrari.stonechallenge.ui.factslist.FactsViewModel
import com.bferrari.stonechallenge.ui.searchfacts.SearchFactsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { FactsViewModel(get(), get()) }

    viewModel { SearchFactsViewModel(get(), get(), get()) }
}
