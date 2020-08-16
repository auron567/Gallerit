package com.example.imagegallery.di

import com.example.imagegallery.data.database.RedditImageDao
import com.example.imagegallery.data.network.RedditImageRemote
import com.example.imagegallery.data.repository.RedditImageRepository
import org.koin.dsl.module

val repositoryModule = module {

    // RedditImageRepository instance
    single { provideRedditImageRepository(get(), get()) }
}

private fun provideRedditImageRepository(
    remote: RedditImageRemote,
    dao: RedditImageDao
): RedditImageRepository {
    return RedditImageRepository(remote, dao)
}
