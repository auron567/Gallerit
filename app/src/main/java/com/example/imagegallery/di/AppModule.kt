package com.example.imagegallery.di

import com.example.imagegallery.contextprovider.DefaultDispatcherProvider
import com.example.imagegallery.contextprovider.DispatcherProvider
import com.example.imagegallery.data.database.RedditImageDao
import com.example.imagegallery.data.network.RedditImageRemote
import com.example.imagegallery.data.repository.RedditImageRepository
import com.example.imagegallery.viewmodel.ImageListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // DispatcherProvider instance
    single<DispatcherProvider> { DefaultDispatcherProvider() }
    // RedditImageRepository instance
    single { provideRedditImageRepository(get(), get()) }
    // ImageListViewModel instance
    viewModel { provideImageListViewModel(get()) }
}

private fun provideRedditImageRepository(
    remote: RedditImageRemote,
    dao: RedditImageDao
): RedditImageRepository {
    return RedditImageRepository(remote, dao)
}

private fun provideImageListViewModel(repository: RedditImageRepository): ImageListViewModel {
    return ImageListViewModel(repository)
}