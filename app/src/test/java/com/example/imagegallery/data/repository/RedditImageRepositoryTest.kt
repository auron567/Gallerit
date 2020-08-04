package com.example.imagegallery.data.repository

import com.example.imagegallery.data.database.RedditImageDao
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.network.RedditImageRemote
import com.example.imagegallery.utils.*
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RedditImageRepositoryTest {

    private lateinit var repository: RedditImageRepository
    @MockK lateinit var remote: RedditImageRemote
    @RelaxedMockK lateinit var dao: RedditImageDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = RedditImageRepository(remote, dao)
    }

    @Test
    fun favoriteImages_returnsSuccess() = runBlocking {
        // Stub dao
        val images = listOf(makeRedditImage(), makeRedditImage(), makeRedditImage())
        val flow = flow { emit(images) }
        coEvery { dao.getAllImages() } returns flow

        // Call repository
        val result = repository.favoriteImages.toList()[0]

        // Assertions
        result.shouldBeSuccess(images)
    }

    @Test
    fun favoriteImages_returnsEmpty() = runBlocking {
        // Stub dao
        val images = emptyList<RedditImage>()
        val flow = flow { emit(images) }
        coEvery { dao.getAllImages() } returns flow

        // Call repository
        val result = repository.favoriteImages.toList()[0]

        // Assertions
        result.shouldBeEmpty()
    }

    @Test
    fun addFavorite_callsDao() = runBlocking {
        // Call repository
        val image = makeRedditImage()
        repository.addFavorite(image)

        // Verify behavior
        coVerify(exactly = 1) {
            dao.insertImage(image)
        }
    }

    @Test
    fun removeFavorite_callsDao() = runBlocking {
        // Call repository
        val image = makeRedditImage()
        repository.removeFavorite(image)

        // Verify behavior
        coVerify(exactly = 1) {
            dao.deleteImage(image)
        }
    }

    @Test
    fun getTopImages_returnsSuccess() = runBlocking {
        // Stub remote
        val images = listOf(makeRedditImage(), makeRedditImage(), makeRedditImage())
        coEvery { remote.getTopImages(any()) } returns images

        // Call repository
        val flow = repository.getTopImages("pics")
        val results = flow.toList()

        // Assertions
        results.shouldHaveSize(2)
        results[0].shouldBeLoading()
        results[1].shouldBeSuccess(images)
    }

    @Test
    fun getTopImages_returnsEmpty() = runBlocking {
        // Stub remote
        coEvery { remote.getTopImages(any()) } returns emptyList()

        // Call repository
        val flow = repository.getTopImages("pics")
        val results = flow.toList()

        // Assertions
        results.shouldHaveSize(2)
        results[0].shouldBeLoading()
        results[1].shouldBeEmpty()
    }

    @Test
    fun getTopImages_returnsError() = runBlocking {
        // Stub remote
        coEvery { remote.getTopImages(any()) } throws IOException()

        // Call repository
        val flow = repository.getTopImages("pics")
        val results = flow.toList()

        // Assertions
        results.shouldHaveSize(2)
        results[0].shouldBeLoading()
        results[1].shouldBeError()
    }
}