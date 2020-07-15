package com.example.imagegallery.di

import com.example.imagegallery.BuildConfig
import com.example.imagegallery.data.network.RedditService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.reddit.com/"

val networkModule = module {

    // HttpLoggingInterceptor instance
    single { provideLoggingInterceptor() }
    // OkHttpClient instance
    single { provideOkHttpClient(get()) }
    // Retrofit instance
    single { provideRetrofit(get()) }
    // RedditService instance
    single { provideRedditService(get()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

private fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideRedditService(retrofit: Retrofit): RedditService {
    return retrofit.create(RedditService::class.java)
}