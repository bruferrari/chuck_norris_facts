package com.bferrari.stonechallenge.injection

import com.bferrari.stonechallenge.ui.factslist.FactsViewModel
import com.bferrari.stonechallenge.ui.searchfacts.SearchFactsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import kotlin.jvm.internal.Reflection

object Modules {
    /**
     * Retrieve given dependency
     */
    @JvmOverloads
    @JvmStatic
    fun <T : Any> get(clazz: Class<T>, name: String = ""): T {
        val kclazz = Reflection.getOrCreateKotlinClass(clazz)

        return StandAloneContext.getKoin().koinContext.get(name, kclazz)
    }

    inline fun <reified T : Any> get(): T = StandAloneContext.getKoin().koinContext.get()
}

val appModule = module {
    viewModel { FactsViewModel(get(), get(), get()) }

    viewModel { SearchFactsViewModel(get(), get(), get()) }
}
