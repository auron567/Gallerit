package com.example.imagegallery.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.imagegallery.adapter.GalleryPagerAdapter
import com.example.imagegallery.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val args: GalleryFragmentArgs by navArgs()

    private val galleryAdapter = GalleryPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        setViewPager()

        return binding.root
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
}