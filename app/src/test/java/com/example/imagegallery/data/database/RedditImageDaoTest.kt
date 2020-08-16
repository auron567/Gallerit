package com.example.imagegallery.data.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.imagegallery.utils.getOrAwaitValue
import com.example.imagegallery.utils.makeRedditImage
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RedditImageDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: RedditImageDao

    private val imageA = makeRedditImage()
    private val imageB = makeRedditImage()
    private val imageC = makeRedditImage()

    @Before
    fun setup() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.redditImageDao()

        // Add initial data
        dao.insertImage(imageA, imageB, imageC)
    }

    @After
    fun teardown() {
        database.close()
        stopKoin()
    }

    @Test
    fun insertImage_addsNewImage() = runBlocking {
        // Add new image
        val newImage = makeRedditImage()
        dao.insertImage(newImage)

        // Get list of images from database
        val result = dao.getAllImages().take(1).toList()
        val images = result[0]

        // Assertions
        images.shouldContainExactly(imageA, imageB, imageC, newImage)
    }

    @Test
    fun deleteImage_removesSelectedImage() = runBlocking {
        // Remove selected image
        dao.deleteImage(imageB)

        // Get list of images from database
        val result = dao.getAllImages().take(1).toList()
        val images = result[0]

        // Assertions
        images.shouldContainExactly(imageA, imageC)
    }

    @Test
    fun isImageExists_returnsTrue() {
        // Check if image is in the database
        val liveData = dao.isImageExists(imageA.id)
        val value = liveData.getOrAwaitValue()

        // Assertions
        value.shouldBeTrue()
    }

    @Test
    fun isImageExists_returnsFalse() {
        // Check if image is in the database
        val image = makeRedditImage()
        val liveData = dao.isImageExists(image.id)
        val value = liveData.getOrAwaitValue()

        // Assertions
        value.shouldBeFalse()
    }
}
