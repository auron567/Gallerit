package com.example.imagegallery.di

import android.app.Application
import androidx.room.Room
import com.example.imagegallery.data.database.AppDatabase
import com.example.imagegallery.data.database.RedditImageDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "gallerit-db"

val databaseModule = module {

    // AppDatabase instance
    single { provideAppDatabase(androidApplication()) }
    // RedditImageDao instance
    single { provideRedditImageDao(get()) }
}

private fun provideAppDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
        .build()
}

private fun provideRedditImageDao(database: AppDatabase): RedditImageDao {
    return database.redditImageDao()
}
