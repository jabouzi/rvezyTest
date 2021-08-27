package com.skanderjabouzi.rvezytest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.skanderjabouzi.rvezytest.R
import com.skanderjabouzi.rvezytest.databinding.FragmentBreedBinding
import com.skanderjabouzi.rvezytest.model.Breed

class BreedFragment : Fragment() {
    private var _binding: FragmentBreedBinding? = null
    private val binding
        get() = _binding

    private var breed: Breed? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            breed = it.getSerializable("breed") as Breed?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breed?.let {
            binding?.image?.load(it.url) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
                transformations(CircleCropTransformation())
            }
            binding?.breedId?.text = it.id
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}