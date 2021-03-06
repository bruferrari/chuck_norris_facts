package com.bferrari.stonechallenge.ui.factslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bferrari.domain.Fact
import com.bferrari.usecases.GetFacts
import com.bferrari.usecases.SaveCategories
import com.bferrari.usecases.SaveFacts
import io.reactivex.disposables.CompositeDisposable

class FactsViewModel(private val getFacts: GetFacts,
                     private val saveFacts: SaveFacts,
                     private val saveCategories: SaveCategories) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val facts = MutableLiveData<List<Fact>>()

    fun getFacts(query: String) = getFacts.invoke(query)
        .doOnNext { facts.postValue(it) }

    fun getCategories() = saveCategories.invoke()

    fun saveFacts(facts: List<Fact>) = saveFacts.invoke(facts)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
