package com.example.imagegallery.data.model

/**
 * Data class that represents a Reddit image.
 */
data class RedditImage(
    val id: String,
    val title: String,
    val subreddit: String,
    val url: String
)