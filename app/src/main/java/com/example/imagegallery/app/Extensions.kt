package com.example.imagegallery.app

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

// Extension functions used throughout the app.

/**
 * Load [uri] in the [ImageView].
 */
fun ImageView.load(uri: String?) {
    Glide.with(context)
        .load(uri)
        .transition(withCrossFade())
        .into(this)
}

/**
 * Load [uri] in the [ImageView], with a low resolution version of image as placeholder.
 */
fun ImageView.loadWithThumbnail(uri: String?, sizeMultiplier: Float = 0.25f) {
    Glide.with(context)
        .load(uri)
        .thumbnail(sizeMultiplier)
        .transition(withCrossFade())
        .into(this)
}