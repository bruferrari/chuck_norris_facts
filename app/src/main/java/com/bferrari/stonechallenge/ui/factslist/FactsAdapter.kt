package com.bferrari.stonechallenge.ui.factslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bferrari.domain.Facts
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.extensions.show
import kotlinx.android.synthetic.main.facts_item.view.*

class FactsAdapter(private val context: Context) : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    var data = emptyList<Facts>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(facts: Facts) {
            itemView.fact.text = facts.value
            facts.category?.let {
                itemView.category.show()
                itemView.category.text = it[0].toUpperCase()
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.facts_item, parent, false)

        return ViewHolder(itemView)
    }
}