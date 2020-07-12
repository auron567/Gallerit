package com.example.imagegallery.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.imagegallery.view.main.CollectionFragment
import com.example.imagegallery.view.main.ImagesFragment

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * ViewPager pages to their respective fragments.
     */
    private val fragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        IMAGES_PAGE_INDEX to { ImagesFragment() },
        COLLECTION_PAGE_INDEX to { CollectionFragment() }
    )

    override fun getItemCount() = fragmentsCreator.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}

const val IMAGES_PAGE_INDEX = 0
const val COLLECTION_PAGE_INDEX = 1