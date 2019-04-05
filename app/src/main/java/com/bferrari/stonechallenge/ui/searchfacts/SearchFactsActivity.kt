package com.bferrari.stonechallenge.ui.searchfacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.extensions.add
import com.bferrari.stonechallenge.ui.factslist.FactsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search_facts.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFactsActivity : AppCompatActivity() {

    private val searchFactsViewModel: SearchFactsViewModel by viewModel()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_facts)

        setupToolbar()

        setupViews()
        getCategories()
        getPastSearches()

    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.search_facts)
        }
    }

    private fun getCategories() {
        searchFactsViewModel.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                suggestionTags.setData(it.map { category -> category.name ?: "" }, ::onCategoryClick)
            }.add(disposable)
    }

    private fun getPastSearches() {
        searchFactsViewModel.getPastSearches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("AQUI", it.toString())
            }.add(disposable)
    }

    private fun onCategoryClick(query: String) {
        setSearchTerm(query)
    }

    private fun setSearchTerm(query: String) {
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
                    setSearchTerm(searchEditText.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> NavUtils.navigateUpFromSameTask(this)
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }

}