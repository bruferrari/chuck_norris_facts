package com.bferrari.stonechallenge.ui.factslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bferrari.domain.Fact
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.extensions.applyTextSizeRule
import kotlinx.android.synthetic.main.facts_item.view.*

class FactsAdapter(private val context: Context,
                   private val shareListener: (Fact) -> Unit) : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    var data = emptyList<Fact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, listener: (Fact) -> Unit, fact: Fact) {
            itemView.fact.text = fact.value
            val categories = fact.category?.let { it } ?: listOf(context.getString(R.string.uncategorized))

            itemView.category.setData(categories, null)

            itemView.shareBtn.setOnClickListener {
                listener.invoke(fact)
            }

            itemView.fact.applyTextSizeRule()
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = data[position]
        holder.bind(context, shareListener, fact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.facts_item, parent, false)

        return ViewHolder(itemView)
    }
}