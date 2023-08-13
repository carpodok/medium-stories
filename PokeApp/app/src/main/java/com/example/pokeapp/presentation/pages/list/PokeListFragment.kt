package com.example.pokeapp.presentation.pages.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentListBinding
import com.example.pokeapp.domain.models.Pokemon
import com.example.pokeapp.presentation.adapters.PokeListAdapter
import com.example.pokeapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokeListFragment : Fragment() {

    lateinit var binding: FragmentListBinding

    val listFragmentViewModel: PokeListViewModel by viewModels()

    var offset = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.apply {
            recycler.apply {
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFragmentViewModel.fetchPokemons()

        observeData()

        binding.apply {

            btnRight.setOnClickListener {
                callNext()
            }

            btnLeft.setOnClickListener {
                callPrevious()
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {

            listFragmentViewModel.pokemonsState.collect {

                when (it.status) {

                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recycler.visibility = View.GONE

                        Log.i("PokeListFragment", "Loading...")
                    }

                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.recycler.visibility = View.VISIBLE

                        it.data?.let { pokeResponse ->
                            val list: List<Pokemon> = pokeResponse.results!!.map { singlePokemon ->

                                Pokemon(
                                    name = singlePokemon.name,
                                    url = singlePokemon.url,
                                )
                            }

                            if (offset <= 0) {
                                binding.apply {
                                    btnLeft.isEnabled = false
                                }
                            } else {
                                binding.btnLeft.isEnabled = true
                            }

                            binding.recycler.adapter = PokeListAdapter(list) { pokemon ->

                                val bundle = bundleOf("name" to pokemon.name)
                                view?.findNavController()
                                    ?.navigate(R.id.action_listFragment_to_detailFragment, bundle)

                            }

                            Log.i("PokeListFragment", "Received poke list.")

                        }
                            ?: run {

                                Log.e("PokeListFragment", "Error: Failed to fetch poke list.")
                            }
                    }


                    // error occurred status
                    else -> {
                        binding.progressBar.visibility = View.GONE
                        binding.recycler.visibility = View.GONE

                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                            .show()

                        Log.e("PokeListFragment", it.message.toString())
                    }
                }
            }
        }
    }

    private fun callNext() {
        offset += 20
        listFragmentViewModel.fetchDataByOffset(offset = offset)
    }

    private fun callPrevious() {

        if (offset <= 0) {
            binding.apply {
                btnLeft.isEnabled = false
                btnRight.isEnabled = true
            }
        } else {
            offset -= 20
            listFragmentViewModel.fetchDataByOffset(offset = offset)
        }

    }
}