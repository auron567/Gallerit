package com.example.imagegallery.data.network

import com.example.imagegallery.data.model.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Used to connect to the Reddit API to fetch posts.
 */
interface RedditService {

    @GET("r/{subreddit}/top.json?raw_json=1")
    suspend fun getTopPosts(
        @Path("subreddit") subreddit: String
    ): RedditResponse
}
