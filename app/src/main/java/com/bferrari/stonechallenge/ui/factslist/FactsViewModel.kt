package com.bferrari.stonechallenge.ui.factslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bferrari.domain.Fact
import com.bferrari.domain.PastSearch
import com.bferrari.usecases.GetFacts
import com.bferrari.usecases.SaveCategories
import com.bferrari.usecases.SavePastSearch
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class FactsViewModel(private val getFacts: GetFacts,
                     private val saveCategories: SaveCategories) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val facts = MutableLiveData<List<Fact>>()

    fun getFacts(query: String) = getFacts.invoke(query)
        .doOnNext { facts.postValue(it) }

    fun getCategories() = saveCategories.invoke()

    override fun onCleared() {
        compositeDisposable.clear()
    }
}