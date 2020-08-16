package com.example.imagegallery.data.model

import com.google.gson.annotations.SerializedName

// Data classes that represents a posts search response from Reddit.
//
// Not all of the fields returned from the API are represented here; only the ones used in this
// project are listed below. For a full list of fields, consult the API documentation at
// https://www.reddit.com/dev/api.

data class RedditResponse(
    @SerializedName("data") val data: RedditData
)

data class RedditData(
    @SerializedName("children") val children: List<RedditChild>
)

data class RedditChild(
    @SerializedName("data") val post: RedditPost
)

data class RedditPost(
    @SerializedName("name") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("subreddit") val subreddit: String,
    @SerializedName("post_hint") val postHint: String,
    @SerializedName("url") val url: String
)
