package com.example.imagegallery.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.imagegallery.app.RedditImages
import com.example.imagegallery.data.model.RedditImage
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [RedditImage] class.
 */
@Dao
interface RedditImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<RedditImages>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(vararg images: RedditImage)

    @Delete
    suspend fun deleteImage(vararg images: RedditImage)

    @Query("SELECT EXISTS(SELECT 1 FROM images WHERE id = :id LIMIT 1)")
    fun isImageExists(id: String): LiveData<Boolean>
}