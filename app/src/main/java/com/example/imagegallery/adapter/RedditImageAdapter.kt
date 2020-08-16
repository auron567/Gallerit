package com.example.imagegallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegallery.app.loadWithThumbnail
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.databinding.ListItemImageBinding

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [RedditImage].
 */
class RedditImageAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<RedditImage, RecyclerView.ViewHolder>(RedditImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RedditImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val image = getItem(position)
        (holder as RedditImageViewHolder).bind(image, onItemClicked)
    }

    class RedditImageViewHolder(private val binding: ListItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: RedditImage, onItemClicked: (Int) -> Unit) {
            binding.root.setOnClickListener {
                onItemClicked(adapterPosition)
            }

            binding.imageView.loadWithThumbnail(image.url)
        }
    }
}

private class RedditImageDiffCallback : DiffUtil.ItemCallback<RedditImage>() {

    override fun areItemsTheSame(oldItem: RedditImage, newItem: RedditImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RedditImage, newItem: RedditImage): Boolean {
        return oldItem == newItem
    }
}
