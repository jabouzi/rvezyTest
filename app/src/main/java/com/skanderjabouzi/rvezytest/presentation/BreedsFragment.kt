package com.skanderjabouzi.rvezytest.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skanderjabouzi.rvezytest.databinding.FragmentBreedsBinding
import com.skanderjabouzi.rvezytest.model.Breed
import com.skanderjabouzi.rvezytest.model.BreedItem
import com.skanderjabouzi.rvezytest.model.BreedX
import com.skanderjabouzi.rvezytest.network.CatRepository

class BreedsFragment : Fragment() {

    private var _binding: FragmentBreedsBinding? = null
    private lateinit var viewModel: BreedsViewModel
    private lateinit var viewModelFactory: BreedsViewModelFactory
    private val binding
        get() = _binding

    private val adapter = BreedsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GridLayoutManager(
            requireContext(), // context
            3, // span count
            RecyclerView.VERTICAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            binding?.reposList?.layoutManager = this
        }
        binding?.reposList?.adapter = adapter
        viewModelFactory = BreedsViewModelFactory(CatRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(BreedsViewModel::class.java)
        viewModel.breeds.observe(viewLifecycleOwner, {
            Log.e("BREEDS", "${it?.get(0)?.url}")
            if (it != null) {
                breedsListItems(it)
            }
        })

        viewModel.status.observe(viewLifecycleOwner, {
            setStatus(it)
        })

        binding?.next?.setOnClickListener {
            viewModel.page = viewModel.page + 1
            viewModel.getReposList(viewModel.limit, viewModel.page)
        }

        binding?.previous?.setOnClickListener {
            if (viewModel.page > 1) {
                viewModel.page = viewModel.page - 1
                viewModel.getReposList(viewModel.limit, viewModel.page)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun breedsListItems(repos: List<Breed>) {
        binding?.progressBar?.isVisible = false
        repos.let {
            adapter.setReposList(repos = it)
        }
    }

    private fun setStatus(status: CatStatus) {
        when (status) {
            CatStatus.LOADING -> {
                binding?.progressBar?.visibility = View.VISIBLE
                binding?.statusImage?.visibility = View.GONE
            }
            CatStatus.ERROR -> {
                binding?.progressBar?.visibility = View.GONE
                binding?.statusImage?.visibility = View.VISIBLE
            }
            CatStatus.DONE -> {
                binding?.progressBar?.visibility = View.GONE
                binding?.statusImage?.visibility = View.GONE
            }
        }
    }
}