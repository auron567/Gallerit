package com.example.imagegallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.view.main.ImageInfoDialogFragment
import kotlinx.coroutines.launch

/**
 * The [ViewModel] to handle a specific [image].
 *
 * Used in [ImageInfoDialogFragment].
 */
class ImageInfoViewModel(
    private val repository: RedditImageRepository,
    val image: RedditImage
) : ViewModel() {

    /**
     * Check if [image] is in the favorites.
     */
    val isFavorite: LiveData<Boolean> = repository.isFavorite(image.id)

    /**
     * Save [image] to favorites.
     */
    fun saveImage() {
        viewModelScope.launch {
            repository.addFavorite(image)
        }
    }

    /**
     * Remove [image] from favorites.
     */
    fun removeImage() {
        viewModelScope.launch {
            repository.removeFavorite(image)
        }
    }
}
