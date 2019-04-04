package com.bferrari.stonechallenge.ui.searchfacts

import androidx.lifecycle.ViewModel
import com.bferrari.usecases.GetCategories

class SearchFactsViewModel(private val getCategories: GetCategories) : ViewModel() {

    fun getCategories() = getCategories.invoke()
}