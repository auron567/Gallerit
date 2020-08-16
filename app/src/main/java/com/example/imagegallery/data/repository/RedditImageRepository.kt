package com.example.imagegallery.data.repository

import androidx.lifecycle.LiveData
import com.example.imagegallery.data.database.RedditImageDao
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.model.RedditImages
import com.example.imagegallery.data.model.Result
import com.example.imagegallery.data.network.RedditImageRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Repository module for handling [RedditImage] data operations.
 */
class RedditImageRepository(
    private val remote: RedditImageRemote,
    private val dao: RedditImageDao
) {

    /**
     * Fetch the list of favorite [RedditImage] from the database.
     */
    val favoriteImages: Flow<Result<RedditImages>>
        get() = dao.getAllImages().map { images ->
            // Emit success or empty result
            Result.successOrEmpty(images)
        }

    /**
     * Add a favorite [image] to the database.
     */
    suspend fun addFavorite(image: RedditImage) {
        dao.insertImage(image)
    }

    /**
     * Remove a favorite [image] from the database.
     */
    suspend fun removeFavorite(image: RedditImage) {
        dao.deleteImage(image)
    }

    /**
     * Check if an image with a certain [id] is in the database.
     */
    fun isFavorite(id: String): LiveData<Boolean> {
        return dao.isImageExists(id)
    }

    /**
     * Call remote source and fetch a list of top [RedditImage] from a [subreddit].
     */
    fun getTopImages(subreddit: String): Flow<Result<RedditImages>> = flow {
        // Emit loading result
        emit(Result.loading())

        // Call remote source and get list of images
        val images = remote.getTopImages(subreddit)

        // Emit success or empty result
        emit(Result.successOrEmpty(images))
    }.catch { e ->
        // Emit error result
        emit(Result.error(e))
    }
}
