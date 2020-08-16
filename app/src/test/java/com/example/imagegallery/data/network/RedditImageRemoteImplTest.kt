package com.example.imagegallery.data.network

import com.example.imagegallery.utils.CoroutineTestRule
import com.example.imagegallery.utils.makeRedditImage
import com.example.imagegallery.utils.makeRedditPost
import com.example.imagegallery.utils.makeRedditResponse
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RedditImageRemoteImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var remote: RedditImageRemoteImpl
    @MockK lateinit var service: RedditService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        remote = RedditImageRemoteImpl(service, coroutineTestRule.testDispatcherProvider)
    }

    @Test
    fun getTopImages_returnsImageList() = coroutineTestRule.runBlockingTest {
        // Stub service
        val posts = listOf(makeRedditPost(), makeRedditPost(false), makeRedditPost())
        val response = makeRedditResponse(posts)
        coEvery { service.getTopPosts(any()) } returns response

        // Call remote
        val images = remote.getTopImages("pics")

        // Assertions
        val expectedImages = listOf(makeRedditImage(posts[0].id), makeRedditImage(posts[2].id))
        images.shouldContainExactly(expectedImages)
    }

    @Test
    fun getTopImages_returnsEmptyList() = coroutineTestRule.runBlockingTest {
        // Stub service
        val posts = listOf(makeRedditPost(false), makeRedditPost(false))
        val response = makeRedditResponse(posts)
        coEvery { service.getTopPosts(any()) } returns response

        // Call remote
        val images = remote.getTopImages("pics")

        // Assertions
        images.shouldBeEmpty()
    }
}
