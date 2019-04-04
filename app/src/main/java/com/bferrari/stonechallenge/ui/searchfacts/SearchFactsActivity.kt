package com.bferrari.stonechallenge.ui.searchfacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.ui.factslist.FactsActivity
import kotlinx.android.synthetic.main.activity_search_facts.*

class SearchFactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_facts)

        setupViews()
    }

    private fun getSearchTerm() {
        val query = searchEditText.text.toString()

        val resultIntent = Intent().apply {
            putExtra(FactsActivity.SEARCH_QUERY, query)
        }

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun setupViews() {
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    getSearchTerm()
                    true
                }
                else -> false
            }
        }
    }

}