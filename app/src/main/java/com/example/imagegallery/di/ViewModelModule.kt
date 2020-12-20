package com.example.imagegallery.di

import com.example.imagegallery.data.model.RedditImage
import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.viewmodel.CollectionViewModel
import com.example.imagegallery.viewmodel.ImageInfoViewModel
import com.example.imagegallery.viewmodel.ImageListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // ImageListViewModel instance
    viewModel { provideImageListViewModel(get()) }
    // CollectionViewModel instance
    viewModel { provideCollectionViewModel(get()) }
    // ImageInfoViewModel instance
    viewModel { (image: RedditImage) -> provideImageInfoViewModel(get(), image) }
}

private fun provideImageListViewModel(repository: RedditImageRepository): ImageListViewModel {
    return ImageListViewModel(repository)
}

private fun provideCollectionViewModel(repository: RedditImageRepository): CollectionViewModel {
    return CollectionViewModel(repository)
}

private fun provideImageInfoViewModel(
    repository: RedditImageRepository,
    image: RedditImage
): ImageInfoViewModel {
    return ImageInfoViewModel(repository, image)
}
