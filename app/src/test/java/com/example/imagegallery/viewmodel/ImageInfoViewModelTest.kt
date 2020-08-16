package com.example.imagegallery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.utils.CoroutineTestRule
import com.example.imagegallery.utils.getOrAwaitValue
import com.example.imagegallery.utils.makeRedditImage
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageInfoViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: ImageInfoViewModel
    @RelaxedMockK lateinit var repository: RedditImageRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun isFavorite_returnsTrue() {
        // Stub repository
        val image = makeRedditImage()
        every { repository.isFavorite(image.id) } returns MutableLiveData(true)

        // Call ViewModel
        viewModel = ImageInfoViewModel(repository, image)
        val liveData = viewModel.isFavorite
        val value = liveData.getOrAwaitValue()

        // Assertions
        value.shouldBeTrue()
    }

    @Test
    fun isFavorite_returnsFalse() {
        // Stub repository
        val image = makeRedditImage()
        every { repository.isFavorite(image.id) } returns MutableLiveData(false)

        // Call ViewModel
        viewModel = ImageInfoViewModel(repository, image)
        val liveData = viewModel.isFavorite
        val value = liveData.getOrAwaitValue()

        // Assertions
        value.shouldBeFalse()
    }

    @Test
    fun saveImage_callsRepository() = coroutineTestRule.runBlockingTest {
        // Initialize ViewModel
        val image = makeRedditImage()
        viewModel = ImageInfoViewModel(repository, image)

        // Call ViewModel
        viewModel.saveImage()

        // Verify behavior
        coVerify(exactly = 1) {
            repository.addFavorite(image)
        }
    }

    @Test
    fun removeImage_callsRepository() = coroutineTestRule.runBlockingTest {
        // Initialize ViewModel
        val image = makeRedditImage()
        viewModel = ImageInfoViewModel(repository, image)

        // Call ViewModel
        viewModel.removeImage()

        // Verify behavior
        coVerify(exactly = 1) {
            repository.removeFavorite(image)
        }
    }
}
