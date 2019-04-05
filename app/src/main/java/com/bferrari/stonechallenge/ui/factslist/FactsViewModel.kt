package com.bferrari.stonechallenge.ui.factslist

import androidx.lifecycle.ViewModel
import com.bferrari.usecases.GetFacts
import com.bferrari.usecases.SaveCategories
import io.reactivex.disposables.CompositeDisposable

class FactsViewModel(private val getFacts: GetFacts,
                     private val saveCategories: SaveCategories) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getFacts(query: String) = getFacts.invoke(query)

    fun getCategories() = saveCategories.invoke()

    override fun onCleared() {
        compositeDisposable.clear()
    }
}