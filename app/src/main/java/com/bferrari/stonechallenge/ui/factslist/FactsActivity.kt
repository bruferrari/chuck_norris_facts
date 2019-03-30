package com.bferrari.stonechallenge.ui.factslist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bferrari.domain.Facts
import com.bferrari.stonechallenge.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class FactsActivity : AppCompatActivity() {

    private val viewModel: FactsViewModel by inject()
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
                Timber.e(it)
            }))
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun setupRecyclerView() {
        adapter = FactsAdapter(this)
        factsRecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        factsRecyclerView.adapter = adapter
    }

    private fun setFacts(facts: List<Facts>) {
        adapter.data = facts
    }
}
