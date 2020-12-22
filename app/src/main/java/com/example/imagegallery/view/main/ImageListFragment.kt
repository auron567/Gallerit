package com.example.imagegallery.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagegallery.R
import com.example.imagegallery.adapter.RedditImageAdapter
import com.example.imagegallery.data.model.RedditImages
import com.example.imagegallery.data.model.Result
import com.example.imagegallery.databinding.FragmentImageListBinding
import com.example.imagegallery.viewmodel.ImageListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageListFragment : Fragment() {

    private lateinit var binding: FragmentImageListBinding

    private val viewModel: ImageListViewModel by viewModel()
    private val imageAdapter = RedditImageAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageListBinding.inflate(inflater, container, false)

        setImagesObserver()
        setImagesRecyclerView()
        setSwipeRefreshLayout()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_images, menu)
        setSearchView(menu)
    }

    /**
     * LiveData observer configuration.
     */
    private fun setImagesObserver() {
        viewModel.images.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    // Set refreshing state
                    binding.swipeRefreshLayout.isRefreshing = true
                }
                is Result.Success -> {
                    showImagesRecyclerView(result.data)
                }
                is Result.Empty -> {
                    val message = getString(R.string.no_image_show)
                    showEmptyView(message)
                }
                is Result.Error -> {
                    val message = if (result.isNetworkError) {
                        getString(R.string.no_internet)
                    } else {
                        getString(R.string.no_image_show)
                    }
                    showEmptyView(message)
                }
            }
        }
    }

    /**
     * Show the empty view with a [message].
     */
    private fun showEmptyView(message: String) {
        with(binding) {
            // Stop refreshing state
            swipeRefreshLayout.isRefreshing = false

            imagesRecyclerView.visibility = View.INVISIBLE
            noDataGroup.visibility = View.VISIBLE

            noDataText.text = message
        }

        // Submit an empty list since there is no data
        imageAdapter.submitList(emptyList())
    }

    /**
     * Show the recycler view and submit a list of [images].
     */
    private fun showImagesRecyclerView(images: RedditImages) {
        with(binding) {
            // Stop refreshing state
            swipeRefreshLayout.isRefreshing = false

            imagesRecyclerView.visibility = View.VISIBLE
            noDataGroup.visibility = View.GONE
        }

        // Submit the list of images
        imageAdapter.submitList(images)
    }

    /**
     * RecyclerView configuration.
     */
    private fun setImagesRecyclerView() {
        binding.imagesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = imageAdapter
        }
    }

    /**
     * RecyclerView item click configuration.
     */
    private fun onItemClicked(position: Int) {
        // Get current list of images
        val images = imageAdapter.currentList.toTypedArray()
        // Get direction and pass list of images and position of item clicked
        val direction =
            HomeViewPagerFragmentDirections.homeViewPagerFragmentToGalleryFragment(images, position)

        // Navigate to GalleryFragment
        findNavController().navigate(direction)
    }

    /**
     * SwipeRefreshLayout configuration.
     *
     * Disable `pull to refresh` gesture and use only progress animation to indicate data loading.
     */
    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.isEnabled = false
    }

    /**
     * SearchView configuration.
     */
    private fun setSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)

        (searchItem.actionView as SearchView).apply {
            // Set hint text
            queryHint = getString(R.string.subreddit)

            // Set listener for user actions
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.setQuery(newText)
                    return true
                }
            })
        }
    }
}
