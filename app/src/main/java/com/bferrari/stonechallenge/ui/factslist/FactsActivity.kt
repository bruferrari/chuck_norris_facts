package com.bferrari.stonechallenge.ui.factslist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.bferrari.domain.Fact
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.extensions.snack
import com.bferrari.stonechallenge.ui.searchfacts.SearchFacts
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        disposable.add(viewModel.getFacts("car")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Timber.d(it.toString())
                setFacts(it)
            }, {
                displayError()
                Timber.e(it)
            }))
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun setupRecyclerView() {
        adapter = FactsAdapter(this, ::share)
        factsRecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        factsRecyclerView.adapter = adapter
    }

    private fun setFacts(facts: List<Fact>) {
        adapter.data = facts
    }

    private fun displayError() {
        rootLayout.snack(getString(R.string.msg_error))
    }

    private fun openSearchActivity() {
        startActivity(Intent(this, SearchFacts::class.java))
    }

    private fun share(fact: Fact) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, fact.value)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.search -> openSearchActivity()
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }
}
