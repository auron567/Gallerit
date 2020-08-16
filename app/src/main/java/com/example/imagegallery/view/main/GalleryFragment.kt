package com.example.imagegallery.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.imagegallery.adapter.GalleryPagerAdapter
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val args: GalleryFragmentArgs by navArgs()

    private val galleryAdapter = GalleryPagerAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        setViewPager()
        setFullscreen()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        exitFullscreen()
    }

    /**
     * ViewPager configuration.
     */
    private fun setViewPager() {
        // Set ViewPager adapter
        binding.viewPager.adapter = galleryAdapter

        // Submit list of received images
        val images = args.images.toList()
        galleryAdapter.submitList(images)

        // Move to the selected image
        val position = args.position
        binding.viewPager.setCurrentItem(position, false)
    }

    /**
     * ViewPager item click configuration.
     */
    private fun onItemClicked(image: RedditImage) {
        // Get direction and pass the image
        val direction = GalleryFragmentDirections.galleryFragmentToImageInfoDialogFragment(image)

        // Navigate to ImageInfoDialogFragment
        findNavController().navigate(direction)
    }

    /**
     * Enable fullscreen mode.
     */
    private fun setFullscreen() {
        activity?.window?.addFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    /**
     * Disable fullscreen mode.
     */
    private fun exitFullscreen() {
        activity?.window?.clearFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
