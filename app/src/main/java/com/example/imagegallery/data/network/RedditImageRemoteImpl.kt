package com.example.imagegallery.data.network

import com.example.imagegallery.contextprovider.DispatcherProvider
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.model.RedditPost
import kotlinx.coroutines.withContext

/**
 * Default implementation of [RedditImageRemote].
 */
class RedditImageRemoteImpl(
    private val service: RedditService,
    private val dispatchers: DispatcherProvider
) : RedditImageRemote {

    /**
     * Fetch a list of top [RedditImage] from a [subreddit].
     */
    override suspend fun getTopImages(subreddit: String) = withContext(dispatchers.default) {
        val response = service.getTopPosts(subreddit)
        val children = response.data.children

        // Transform list of posts into list of images
        children.mapNotNull { it.post.toImage() }
    }

    /**
     * Transform [RedditPost] to [RedditImage] if compatible, otherwise return `null`.
     */
    private fun RedditPost.toImage(): RedditImage? {
        return if (postHint == IMAGE_TYPE) {
            RedditImage(id, title, subreddit, url)
        } else {
            null
        }
    }
}

private const val IMAGE_TYPE = "image"
