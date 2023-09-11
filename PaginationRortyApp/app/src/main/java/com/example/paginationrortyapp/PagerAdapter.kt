package com.example.paginationrortyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationrortyapp.databinding.ItemReyclerviewBinding
import com.example.paginationrortyapp.model.ResultsResponse
import javax.inject.Inject

class PagerAdapter @Inject constructor(
    val onItemClickListener: ((ResultsResponse) -> Unit),
) : PagingDataAdapter<ResultsResponse, PagerAdapter.ViewHolder>(
    ListComparator
) {
    object ListComparator : DiffUtil.ItemCallback<ResultsResponse>() {
        override fun areItemsTheSame(oldItem: ResultsResponse, newItem: ResultsResponse): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResultsResponse,
            newItem: ResultsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(
        binding: ItemReyclerviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val name: TextView = itemView.findViewById(R.id.listItemName)

        init {

            itemView.setOnClickListener {
                val position = adapterPosition
                onItemClickListener.invoke(getItem(position)!!)
            }
        }

        fun bind(item: ResultsResponse) {
            name.text = item.name
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemReyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }
}
