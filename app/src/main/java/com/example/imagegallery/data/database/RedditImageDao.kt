package com.example.imagegallery.data.database

import androidx.room.*
import com.example.imagegallery.data.model.RedditImage
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [RedditImage] class.
 */
@Dao
interface RedditImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<List<RedditImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: RedditImage)

    @Delete
    suspend fun deleteImage(image: RedditImage)
}