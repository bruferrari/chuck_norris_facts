package com.bferrari.stonechallenge.ui.searchfacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bferrari.domain.PastSearch
import com.bferrari.stonechallenge.R
import kotlinx.android.synthetic.main.search_item.view.*

class SearchFactsAdapter(private val listener: (String) -> Unit)
    : RecyclerView.Adapter<SearchFactsAdapter.ViewHolder>() {

    var data = emptyList<PastSearch>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pastSearch: PastSearch, listener: (String) -> Unit) {
            itemView.search.text = pastSearch.query
            itemView.search.setOnClickListener {
                listener.invoke(pastSearch.query)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pastSearch = data[position]
        holder.bind(pastSearch, listener)
    }

}