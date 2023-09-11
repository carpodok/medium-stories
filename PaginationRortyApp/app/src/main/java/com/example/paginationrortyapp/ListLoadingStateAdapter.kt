package com.example.paginationrortyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationrortyapp.databinding.ItemNetworkStateBinding

class ListLoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ListLoadingStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {

        val view =
            ItemNetworkStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NetworkStateItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    inner class NetworkStateItemViewHolder(
        private val binding: ItemNetworkStateBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                progressBar.isVisible = loadState !is LoadState.Error

                retryButton.isVisible = loadState !is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error

                errorMsg.isVisible = loadState !is LoadState.Loading
                errorMsg.isVisible = loadState is LoadState.Error

            }
        }
    }
}
