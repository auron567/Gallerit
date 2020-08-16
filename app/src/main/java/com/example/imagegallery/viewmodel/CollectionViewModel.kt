package com.example.imagegallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.model.RedditImages
import com.example.imagegallery.data.model.Result
import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.view.main.CollectionFragment

/**
 * The [ViewModel] for fetching the list of favorite [RedditImage].
 *
 * Used in [CollectionFragment].
 */
class CollectionViewModel(repository: RedditImageRepository) : ViewModel() {

    /**
     * The current list of favorite images.
     */
    val favoriteImages: LiveData<Result<RedditImages>> = repository.favoriteImages.asLiveData()
}
