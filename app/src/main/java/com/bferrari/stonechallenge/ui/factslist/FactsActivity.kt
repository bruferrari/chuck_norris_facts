package com.bferrari.stonechallenge.ui.factslist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bferrari.domain.Fact
import com.bferrari.stonechallenge.EspressoIdlingResource
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.StoneChallengeApplication
import com.bferrari.stonechallenge.extensions.*
import com.bferrari.stonechallenge.ui.searchfacts.SearchFactsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FactsActivity : AppCompatActivity() {

    private val viewModel: FactsViewModel by viewModel()

    private lateinit var adapter: FactsAdapter

    private val disposable = CompositeDisposable()

    companion object {
        const val SEARCH_QUERY = "SEARCH_QUERY"
        const val SEARCH_REQUEST_CODE = 99
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        getCategories()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun setupRecyclerView() {
        adapter = FactsAdapter(this, ::share)

        displayEmptyState()

        factsRecyclerView.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        factsRecyclerView.adapter = adapter
    }

    private fun setFacts(facts: List<Fact>) {
        if (facts.isNotEmpty()) {
            adapter.data = facts
            hideEmptyState()
        } else {
            displayEmptyState()
        }
    }

    private fun getFacts(query: String) {
        EspressoIdlingResource.idlingResource.increment()

        viewModel.getFacts(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    setLoadingIndicator(true)
                    hideEmptyState()
                }
                .doOnComplete {
                    setLoadingIndicator(false)
                    if (!EspressoIdlingResource.idlingResource.isIdleNow)
                        EspressoIdlingResource.idlingResource.decrement()
                }
                .doOnError { setLoadingIndicator(false) }
                .subscribe ({ setFacts(it) }, ::handleError)
                .add(disposable)
    }

    private fun getCategories() {
        viewModel.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(::handleError)
            .subscribe()
            .add(disposable)
    }

    private fun setLoadingIndicator(display: Boolean) {
        if (display)
            progressBar.show()
        else
            progressBar.hide()
    }

    private fun handleError(error: Throwable) {
        rootLayout.snack(R.string.msg_error)
        Timber.e(error)
    }

    private fun openSearchActivity(requestCode: Int) {
        navigateForResult<SearchFactsActivity>(requestCode)
    }

    private fun share(fact: Fact) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, fact.value)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_label)))
    }

    private fun displayEmptyState() {
        factsRecyclerView.hide()
        emptyState.show()
    }

    private fun hideEmptyState() {
        factsRecyclerView.show()
        emptyState.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.search -> openSearchActivity(SEARCH_REQUEST_CODE)
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SEARCH_REQUEST_CODE) {
                adapter.data = emptyList()
                data?.getStringExtra(SEARCH_QUERY)?.let {
                    getFacts(it)
                }
            }

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let {
            setFacts(viewModel.facts.value ?: return)
        }
    }
}
