package com.example.imagegallery.viewmodel

import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.utils.CoroutineTestRule
import com.example.imagegallery.utils.makeRedditImage
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageInfoViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: ImageInfoViewModel
    @RelaxedMockK lateinit var repository: RedditImageRepository

    private val image = makeRedditImage()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = ImageInfoViewModel(repository, image)
    }

    @Test
    fun saveImage_callsRepository() = coroutineTestRule.runBlockingTest {
        // Call ViewModel
        viewModel.saveImage()

        // Verify behavior
        coVerify(exactly = 1) {
            repository.addFavorite(image)
        }
    }

    @Test
    fun removeImage_callsRepository() = coroutineTestRule.runBlockingTest {
        // Call ViewModel
        viewModel.removeImage()

        // Verify behavior
        coVerify(exactly = 1) {
            repository.removeFavorite(image)
        }
    }
}