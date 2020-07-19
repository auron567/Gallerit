package com.example.imagegallery.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents a Reddit image.
 */
@Entity(tableName = "images")
data class RedditImage(
    @PrimaryKey val id: String,
    val title: String,
    val subreddit: String,
    val url: String
)