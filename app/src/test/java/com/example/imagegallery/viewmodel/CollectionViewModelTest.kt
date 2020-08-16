package com.example.imagegallery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.imagegallery.data.model.Result
import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.utils.CoroutineTestRule
import com.example.imagegallery.utils.getOrAwaitValue
import com.example.imagegallery.utils.makeRedditImage
import com.example.imagegallery.utils.shouldBeEmpty
import com.example.imagegallery.utils.shouldBeSuccess
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CollectionViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: CollectionViewModel
    @MockK lateinit var repository: RedditImageRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun favoriteImages_returnsSuccess() = coroutineTestRule.runBlockingTest {
        // Stub repository
        val images = listOf(makeRedditImage(), makeRedditImage(), makeRedditImage())
        val flow = flow { emit(Result.success(images)) }
        every { repository.favoriteImages } returns flow

        // Call ViewModel
        viewModel = CollectionViewModel(repository)
        val liveData = viewModel.favoriteImages
        val result = liveData.getOrAwaitValue()

        // Assertions
        result.shouldBeSuccess(images)
    }

    @Test
    fun favoriteImages_returnsEmpty() = coroutineTestRule.runBlockingTest {
        // Stub repository
        val flow = flow { emit(Result.empty()) }
        every { repository.favoriteImages } returns flow

        // Call ViewModel
        viewModel = CollectionViewModel(repository)
        val liveData = viewModel.favoriteImages
        val result = liveData.getOrAwaitValue()

        // Assertions
        result.shouldBeEmpty()
    }
}
