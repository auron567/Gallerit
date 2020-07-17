package com.example.imagegallery.data.network

import com.example.imagegallery.data.model.RedditImage

/**
 * Remote entry point to fetch Reddit images.
 */
interface RedditImageRemote {

    suspend fun getTopImages(subreddit: String): List<RedditImage>
}