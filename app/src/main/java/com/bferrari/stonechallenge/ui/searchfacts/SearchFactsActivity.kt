package com.bferrari.stonechallenge.ui.searchfacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bferrari.domain.PastSearch
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.extensions.add
import com.bferrari.stonechallenge.ui.factslist.FactsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search_facts.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFactsActivity : AppCompatActivity() {

    private val viewModel: SearchFactsViewModel by viewModel()
    private lateinit var searchAdapter: PastSearchesAdapter

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
            title = getString(R.string.search_facts_label)
        }
    }

    private fun getCategories() {
        viewModel.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                suggestionTags.setData(it.map { category -> category.name ?: "" }, ::onCategoryClick)
            }.add(disposable)
    }

    private fun getPastSearches() {
        viewModel.getPastSearches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::setPastSearches)
            .add(disposable)
    }

    private fun onCategoryClick(query: String) {
        setSearchTerm(query)
    }

    private fun onPastSearchClick(query: String) {
        setSearchTerm(query)
    }

    private fun setSearchTerm(query: String) {
        val resultIntent = Intent().apply {
            putExtra(FactsActivity.SEARCH_QUERY, query)
        }

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun setPastSearches(searches: List<PastSearch>) {
        searchAdapter.data = searches
    }

    private fun setupViews() {
        setupPastSearches()

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    val query = searchEditText.text.toString()
                    setSearchTerm(query)
                    saveSearch(query)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupPastSearches() {
        searchAdapter = PastSearchesAdapter(::onPastSearchClick)

        pastSearchesRv.layoutManager = LinearLayoutManager(
                this, RecyclerView.VERTICAL, false)
        pastSearchesRv.adapter = searchAdapter
    }

    private fun saveSearch(query: String) {
        viewModel.savePastSearch(PastSearch(query))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .add(disposable)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> onBackPressed()
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }

}
