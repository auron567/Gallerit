package com.example.imagegallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.imagegallery.app.load
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.model.RedditImages
import com.example.imagegallery.databinding.ListItemGalleryBinding

/**
 * Adapter class [RecyclerView.Adapter] for [ViewPager2] which binds [RedditImage].
 */
class GalleryPagerAdapter(
    private val onItemClicked: (RedditImage) -> Unit,
    private val images: MutableList<RedditImage> = mutableListOf()
) : RecyclerView.Adapter<GalleryPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], onItemClicked)
    }

    fun submitList(list: RedditImages) {
        this.images.clear()
        this.images.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: RedditImage, onItemClicked: (RedditImage) -> Unit) {
            binding.imageView.setOnClickListener {
                onItemClicked(image)
            }

            binding.imageView.load(image.url)
        }
    }
}
