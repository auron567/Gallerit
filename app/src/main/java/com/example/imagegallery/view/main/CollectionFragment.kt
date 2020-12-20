package com.example.imagegallery.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagegallery.adapter.RedditImageAdapter
import com.example.imagegallery.data.model.RedditImages
import com.example.imagegallery.data.model.Result
import com.example.imagegallery.databinding.FragmentCollectionBinding
import com.example.imagegallery.viewmodel.CollectionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionFragment : Fragment() {

    private lateinit var binding: FragmentCollectionBinding

    private val viewModel: CollectionViewModel by viewModel()
    private val favoritesAdapter = RedditImageAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)

        setFavoritesObserver()
        setFavoritesRecyclerView()

        return binding.root
    }

    /**
     * LiveData observer configuration.
     */
    private fun setFavoritesObserver() {
        viewModel.favoriteImages.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    showFavoritesRecyclerView(result.data)
                }
                is Result.Empty -> {
                    showEmptyView()
                }
            }
        }
    }

    /**
     * Show the empty view.
     */
    private fun showEmptyView() {
        with(binding) {
            favoritesRecyclerView.visibility = View.INVISIBLE
            noDataGroup.visibility = View.VISIBLE
        }

        // Submit an empty list since there is no data
        favoritesAdapter.submitList(emptyList())
    }

    /**
     * Show the recycler view and submit a list of [images].
     */
    private fun showFavoritesRecyclerView(images: RedditImages) {
        with(binding) {
            favoritesRecyclerView.visibility = View.VISIBLE
            noDataGroup.visibility = View.GONE
        }

        // Submit the list of images
        favoritesAdapter.submitList(images)
    }

    /**
     * RecyclerView configuration.
     */
    private fun setFavoritesRecyclerView() {
        binding.favoritesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = favoritesAdapter
        }
    }

    /**
     * RecyclerView item click configuration.
     */
    private fun onItemClicked(position: Int) {
        // Get current list of images
        val images = favoritesAdapter.currentList.toTypedArray()
        // Get direction and pass list of images and position of item clicked
        val direction =
            HomeViewPagerFragmentDirections.homeViewPagerFragmentToGalleryFragment(images, position)

        // Navigate to GalleryFragment
        findNavController().navigate(direction)
    }
}
