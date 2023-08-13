package com.example.pokeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.domain.models.Pokemon

class PokeListAdapter(val names: List<Pokemon>, val onItemClickListener: (Pokemon) -> Unit) :
    RecyclerView.Adapter<PokeListAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_poke, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(names[position])
    }

    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var urlTextView: TextView = itemView.findViewById(R.id.urlTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                onItemClickListener.invoke(names[position])
            }
        }

        fun bind(item: Pokemon) {
            nameTextView.text = item.name
            urlTextView.text = item.url
        }

    }
}