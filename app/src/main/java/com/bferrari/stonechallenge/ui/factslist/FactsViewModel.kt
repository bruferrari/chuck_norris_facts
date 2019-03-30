package com.bferrari.stonechallenge.ui.factslist

import android.arch.lifecycle.ViewModel
import com.bferrari.data.FactsDataSource
import io.reactivex.disposables.CompositeDisposable

class FactsViewModel(private val dataSource: FactsDataSource) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getFacts(query: String) = dataSource.getFacts(query)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}