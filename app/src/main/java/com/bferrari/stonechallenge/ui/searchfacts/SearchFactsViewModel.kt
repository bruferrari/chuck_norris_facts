package com.bferrari.stonechallenge.ui.searchfacts

import androidx.lifecycle.ViewModel
import com.bferrari.domain.PastSearch
import com.bferrari.usecases.GetCategories
import com.bferrari.usecases.GetPastSearches
import com.bferrari.usecases.SavePastSearch

class SearchFactsViewModel(private val getCategories: GetCategories,
                           private val getPastSearches: GetPastSearches,
                           private val savePastSearch: SavePastSearch) : ViewModel() {

    fun getCategories() = getCategories.invoke()

    fun getPastSearches() = getPastSearches.invoke()

    fun savePastSearch(pastSearch: PastSearch) = savePastSearch.invoke(pastSearch)
}