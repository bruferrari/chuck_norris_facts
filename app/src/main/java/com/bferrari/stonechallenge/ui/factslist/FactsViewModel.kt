package com.bferrari.stonechallenge.ui.factslist

import android.arch.lifecycle.ViewModel
import com.bferrari.usecases.GetFacts
import io.reactivex.disposables.CompositeDisposable

class FactsViewModel(private val getFactsUseCase: GetFacts) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getFacts(query: String) = getFactsUseCase.invoke(query)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}