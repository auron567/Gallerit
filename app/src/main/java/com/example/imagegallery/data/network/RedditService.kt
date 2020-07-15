package com.example.imagegallery.data.network

import com.example.imagegallery.data.model.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditService {

    @GET("r/{subreddit}/top.json")
    suspend fun getTopPosts(
        @Path("subreddit") subreddit: String
    ): RedditResponse
}