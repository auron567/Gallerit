package com.example.imagegallery.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imagegallery.data.model.RedditImage

/**
 * The Room database for this app.
 */
@Database(entities = [RedditImage::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun redditImageDao(): RedditImageDao
}
