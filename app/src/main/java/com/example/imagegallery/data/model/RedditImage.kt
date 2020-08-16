package com.example.imagegallery.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Data class that represents a Reddit image.
 */
@Entity(tableName = "images")
@Parcelize
data class RedditImage(
    @PrimaryKey val id: String,
    val title: String,
    val subreddit: String,
    val url: String
) : Parcelable

typealias RedditImages = List<RedditImage>
