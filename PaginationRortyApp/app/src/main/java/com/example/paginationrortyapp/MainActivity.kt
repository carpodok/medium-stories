package com.example.paginationrortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationrortyapp.databinding.ActivityMainBinding
import com.example.paginationrortyapp.model.ResultsResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        pagerAdapter = PagerAdapter(
            onItemClickListener = {
                onItemClicked(result = it)
            },
        )

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)


            adapter = pagerAdapter.withLoadStateFooter(
                footer = ListLoadingStateAdapter {
                    pagerAdapter.retry()
                }
            )

            //adapter = pagerAdapter

        }

        binding.mainRetryBtn.setOnClickListener {
            onRetryBtnClicked()
        }

        pagerAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {

                    binding.mainRetryBtn.visibility = View.INVISIBLE
                    binding.mainErrorMsg.visibility = View.INVISIBLE
                    binding.mainProgressBar.visibility = View.VISIBLE

                    binding.listRecyclerView.visibility = View.GONE
                }

                is LoadState.Error -> {

                    binding.mainRetryBtn.visibility = View.VISIBLE
                    binding.mainErrorMsg.visibility = View.VISIBLE
                    binding.mainProgressBar.visibility = View.INVISIBLE

                    binding.listRecyclerView.visibility = View.GONE
                }

                is LoadState.NotLoading -> {
                    binding.mainRetryBtn.visibility = View.GONE
                    binding.mainErrorMsg.visibility = View.GONE
                    binding.mainProgressBar.visibility = View.GONE

                    binding.listRecyclerView.visibility = View.VISIBLE
                }
            }
        }


        observeData()

    }

    private fun onRetryBtnClicked() {
        pagerAdapter.retry()
    }

    private fun observeData() {

        lifecycleScope.launch {

            viewModel.resultsState.collectLatest {

                it.collectLatest { pagingData ->
                    pagerAdapter.submitData(pagingData)
                }

            }
        }
    }

    private fun onItemClicked(result: ResultsResponse) {
        Toast.makeText(this, "Clicked Item ${result.name}", Toast.LENGTH_SHORT).show()
    }


}