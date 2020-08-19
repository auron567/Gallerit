package com.example.imagegallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.model.RedditImages
import com.example.imagegallery.data.model.Result
import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.view.main.ImageListFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

/**
 * The [ViewModel] for fetching a list of top [RedditImage] from a subreddit.
 *
 * Used in [ImageListFragment].
 */
class ImageListViewModel(private val repository: RedditImageRepository) : ViewModel() {

    /**
     * The current text (subreddit) to search.
     */
    private val query = MutableStateFlow("")

    /**
     * A list of [RedditImage] that updates based on the current text.
     */
    val images: LiveData<Result<RedditImages>> = query
        // Discard text typed in a very short time to avoid many network calls
        .debounce(DEBOUNCE_MILLIS)
        // Filter empty text to avoid unnecessary network call
        .filter { text ->
            text.isNotEmpty()
        }
        // When a new text is set, transform it in Result<RedditImages> by triggering a
        // network call
        .flatMapLatest { text ->
            repository.getTopImages(text)
        }
        // Create a LiveData from Flow
        .asLiveData()

    /**
     * Set the new [text] to search.
     */
    fun setQuery(text: String) {
        query.value = text
    }
}

private const val DEBOUNCE_MILLIS = 700L
