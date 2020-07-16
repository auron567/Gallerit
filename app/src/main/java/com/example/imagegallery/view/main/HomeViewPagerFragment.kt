package com.example.imagegallery.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.imagegallery.R
import com.example.imagegallery.adapter.COLLECTION_PAGE_INDEX
import com.example.imagegallery.adapter.HomePagerAdapter
import com.example.imagegallery.adapter.IMAGE_LIST_PAGE_INDEX
import com.example.imagegallery.databinding.FragmentHomeViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IndexOutOfBoundsException

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        // Set ViewPager adapter
        viewPager.adapter = HomePagerAdapter(this)

        // Set TabLayout icons and texts and attach it to ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    /**
     * Get TabLayout icons.
     */
    private fun getTabIcon(position: Int): Int {
        return when (position) {
            IMAGE_LIST_PAGE_INDEX -> R.drawable.images_tab_selector
            COLLECTION_PAGE_INDEX -> R.drawable.collection_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    /**
     * Get TabLayout titles.
     */
    private fun getTabTitle(position: Int): String {
        return when (position) {
            IMAGE_LIST_PAGE_INDEX -> getString(R.string.images)
            COLLECTION_PAGE_INDEX -> getString(R.string.my_collection)
            else -> throw IndexOutOfBoundsException()
        }
    }
}