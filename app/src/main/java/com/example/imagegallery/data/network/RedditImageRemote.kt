package com.example.imagegallery.data.network

import com.example.imagegallery.data.model.RedditImages

/**
 * Remote entry point to fetch Reddit images.
 */
interface RedditImageRemote {

    suspend fun getTopImages(subreddit: String): RedditImages
}
