package com.example.imagegallery.utils

import com.example.imagegallery.data.model.RedditChild
import com.example.imagegallery.data.model.RedditData
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.model.RedditPost
import com.example.imagegallery.data.model.RedditResponse
import java.util.UUID

fun makeRedditResponse(posts: List<RedditPost>): RedditResponse {
    val children = mutableListOf<RedditChild>()
    for (post in posts) {
        children.add(RedditChild(post))
    }

    return RedditResponse(RedditData(children))
}

fun makeRedditPost(
    isImage: Boolean = true,
    id: String = "${UUID.randomUUID()}",
    title: String = "",
    subreddit: String = "",
    url: String = ""
): RedditPost {
    val postHint = if (isImage) "image" else ""
    return RedditPost(id, title, subreddit, postHint, url)
}

fun makeRedditImage(
    id: String = "${UUID.randomUUID()}",
    title: String = "",
    subreddit: String = "",
    url: String = ""
): RedditImage {
    return RedditImage(id, title, subreddit, url)
}
