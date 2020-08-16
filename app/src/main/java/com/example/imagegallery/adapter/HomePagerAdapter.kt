package com.example.imagegallery.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.imagegallery.view.main.CollectionFragment
import com.example.imagegallery.view.main.ImageListFragment

/**
 * Adapter class [FragmentStateAdapter] for [ViewPager2].
 */
class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * ViewPager pages to their respective fragments.
     */
    private val fragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        IMAGE_LIST_PAGE_INDEX to { ImageListFragment() },
        COLLECTION_PAGE_INDEX to { CollectionFragment() }
    )

    override fun getItemCount() = fragmentsCreator.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}

const val IMAGE_LIST_PAGE_INDEX = 0
const val COLLECTION_PAGE_INDEX = 1
