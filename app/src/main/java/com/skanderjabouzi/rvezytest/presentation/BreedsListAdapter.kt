package com.skanderjabouzi.rvezytest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.transform.CircleCropTransformation
import com.skanderjabouzi.rvezytest.R
import com.skanderjabouzi.rvezytest.databinding.BreedsItemBinding
import com.skanderjabouzi.rvezytest.model.Breed

class BreedsListAdapter(): RecyclerView.Adapter<BreedsListAdapter.ReposViewHolder>() {

    private lateinit var binding: BreedsItemBinding
    private var breeds = mutableListOf<Breed>()
    private lateinit var imageLoader: ImageLoader
    inner class ReposViewHolder(val binding: BreedsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(breed: Breed) {
            binding.breedImage.load(breed.url) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        binding = BreedsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ReposViewHolder(binding)
        binding.root.setOnClickListener {
            var bundle = bundleOf("breed" to breeds[holder.adapterPosition])
            binding.root.findNavController().navigate(R.id.breedFragment, bundle)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(breeds[position])
    }

    override fun getItemCount() = breeds.size

    fun setReposList(repos: List<Breed>) {
        this.breeds.clear()
        this.breeds.addAll(repos)
        notifyDataSetChanged()
    }
}